package com.microservice.chapter3_license.service

import com.microservice.chapter3_license.model.License
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random

@Service
class LicenseService(private val message: MessageSource) {

    fun getLicense(licenseId: String, organizationId: String): License {
        return License(
            id = Random.nextInt(1000),
            licenseId = licenseId,
            description = "Software product",
            organizationId = organizationId,
            productName = "Product",
            licenseType = "Type"
        )
    }

    fun createLicense(license: License?, organizationId: String, locale: Locale): String? {
        return license?.let {
            it.organizationId = organizationId
            String.format(
                message.getMessage("license.create.message", null, locale),
                it.toString()
            )
        }
    }

    fun updateLicense(license: License?, organizationId: String, locale: Locale): String? {
        return license?.let {
            it.organizationId = organizationId
            String.format(
                message.getMessage("license.update.message", null, locale),
                it.toString()
            )
        }
    }

    fun deleteLicense(licenseId: String, organizationId: String, locale: Locale): String {
        return String.format(
            message.getMessage("license.delete.message", null, locale),
            licenseId,
            organizationId
        )
    }
}