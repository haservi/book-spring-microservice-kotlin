package com.microservice.chapter7_organization_service.service

import com.microservice.chapter7_organization_service.config.ServiceConfig
import com.microservice.chapter7_organization_service.model.License
import com.microservice.chapter7_organization_service.repository.LicenseRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeoutException

@Service
class LicenseService(
    @Autowired private val messages: MessageSource,
    @Autowired private val licenseRepository: LicenseRepository,
    @Autowired private val config: ServiceConfig
) {

    private val logger = KotlinLogging.logger {}

    private fun randomlyRunLog() {
        val randomNum = (1..3).random()
        if (randomNum == 3) sleep()
    }

    private fun sleep() {
        try {
            logger.info { "Sleep" }
            Thread.sleep(1000)
            throw TimeoutException()
        } catch (e: InterruptedException) {
            logger.error { "Error: ${e.message}" }
        }
    }

    fun getLicensesByOrganization(organizationId: String): List<License> {
        randomlyRunLog()
        return licenseRepository.findByOrganizationId(organizationId)
    }


    fun getLicense(licenseId: String, organizationId: String): License {
        val license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId)
            ?: throw IllegalArgumentException(
                String.format(
                    messages.getMessage("license.search.error.message", null, Locale.getDefault()),
                    licenseId,
                    organizationId
                )
            )
        return license.withComment(config.property)
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
        val license = licenseRepository.findById(licenseId)
            .orElseThrow { IllegalArgumentException("License not found with id: $licenseId") }

        licenseRepository.delete(license)
        return String.format(messages.getMessage("license.delete.message", null, Locale.getDefault()), licenseId)
    }

}