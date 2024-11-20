package com.microservice.chapter5_license.service

import com.microservice.chapter5_license.config.ServiceConfig
import com.microservice.chapter5_license.model.License
import com.microservice.chapter5_license.repository.LicenseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.*

@Service
class LicenseService(
    @Autowired private val messages: MessageSource,
    @Autowired private val licenseRepository: LicenseRepository,
    @Autowired private val config: ServiceConfig
) {

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