package com.microservice.chapter8_license_service.service

import com.microservice.chapter8_license_service.config.ServiceConfig
import com.microservice.chapter8_license_service.model.License
import com.microservice.chapter8_license_service.model.Organization
import com.microservice.chapter8_license_service.repository.LicenseRepository
import com.microservice.chapter8_license_service.service.client.OrganizationDiscoveryClient
import com.microservice.chapter8_license_service.service.client.OrganizationFeignClient
import com.microservice.chapter8_license_service.service.client.OrganizationRestTemplateClient
import io.github.oshai.kotlinlogging.KotlinLogging
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.*

@Service
class LicenseService(
    private val messages: MessageSource,
    private val licenseRepository: LicenseRepository,
    private val config: ServiceConfig,
    private val organizationFeignClient: OrganizationFeignClient,
    private val organizationRestClient: OrganizationRestTemplateClient,
    private val organizationDiscoveryClient: OrganizationDiscoveryClient
) {

    private val logger = KotlinLogging.logger {}

    fun getLicense(licenseId: String, organizationId: String, clientType: String): License {
        val license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId)
            ?: throw IllegalArgumentException(
                String.format(
                    messages.getMessage("license.search.error.message", null, Locale.getDefault()),
                    licenseId,
                    organizationId
                )
            )

        val organization = retrieveOrganizationInfo(organizationId, clientType)
        organization?.let {
            license.organizationName = it.name
            license.contactName = it.contactName
            license.contactEmail = it.contactEmail
            license.contactPhone = it.contactPhone
        }

        return license.withComment(config.property)
    }

    private fun retrieveOrganizationInfo(organizationId: String, clientType: String): Organization? {
        return when (clientType) {
            "feign" -> {
                println("I am using the feign client")
                organizationFeignClient.getOrganization(organizationId)
            }

            "rest" -> {
                println("I am using the rest client")
                organizationRestClient.getOrganization(organizationId)
            }

            "discovery" -> {
                println("I am using the discovery client")
                organizationDiscoveryClient.getOrganization(organizationId)
            }

            else -> {
                organizationRestClient.getOrganization(organizationId)
            }
        }
    }

    fun createLicense(license: License): License {
        license.licenseId = UUID.randomUUID().toString()
        licenseRepository.save(license)
        return license.withComment(config.property)
    }

    fun updateLicense(license: License): License {
        licenseRepository.save(license)
        return license.withComment(config.property)
    }

    fun deleteLicense(licenseId: String): String {
        licenseRepository.delete(License().apply { this.licenseId = licenseId })
        return String.format(messages.getMessage("license.delete.message", null, Locale.getDefault()), licenseId)
    }

    //    @CircuitBreaker(name = "licenseService", fallbackMethod = "buildFallbackLicenseList")
    @RateLimiter(name = "licenseService", fallbackMethod = "buildFallbackLicenseList")
//    @Retry(name = "retryLicenseService", fallbackMethod = "buildFallbackLicenseList")
//    @Bulkhead(
//        name = "bulkheadLicenseService",
//        type = Bulkhead.Type.THREADPOOL,
//        fallbackMethod = "buildFallbackLicenseList"
//    )
    fun getLicensesByOrganization(organizationId: String): List<License> {
        logger.info { "getLicensesByOrganization Correlation id: $organizationId" }
//        randomlyRunLong()
        return licenseRepository.findByOrganizationId(organizationId)
    }

    private fun buildFallbackLicenseList(organizationId: String, t: Throwable): List<License> {
        val fallbackList = mutableListOf<License>()
//        logger.error { "getLicensesByOrganization Correlation id: ${UserContextHolder.getContext().correlationId}" }
        val license = License().apply {
            this.licenseId = "0000000-00-00000"
            this.organizationId = organizationId
            this.productName = "Sorry no licensing information currently available"
        }
        fallbackList.add(license)
        return fallbackList
    }

    private fun randomlyRunLong() {
//        val randomNum = (1..3).random()
//        if (randomNum == 3)
        sleep()
    }

    private fun sleep() {
        try {
            Thread.sleep(1000000)
        } catch (e: InterruptedException) {
            logger.error { e.message }
        }
    }
}
