package com.microservice.chapter8_license_service.controller

import com.microservice.chapter8_license_service.model.License
import com.microservice.chapter8_license_service.service.LicenseService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/organization/{organizationId}/license")
class LicenseController(@Autowired private val licenseService: LicenseService) {

    @GetMapping("/{licenseId}/{clientType}")
    fun getLicensesWithClient(
        @PathVariable organizationId: String,
        @PathVariable licenseId: String,
        @PathVariable clientType: String
    ): License {
        return licenseService.getLicense(licenseId, organizationId, clientType)
    }

    @PutMapping
    fun updateLicense(@RequestBody request: License): ResponseEntity<License> {
        return ResponseEntity.ok(licenseService.updateLicense(request))
    }

    @PostMapping
    fun createLicense(@RequestBody request: License): ResponseEntity<License> {
        return ResponseEntity.ok(licenseService.createLicense(request))
    }

    @DeleteMapping("/{licenseId}")
    fun deleteLicense(@PathVariable licenseId: String): ResponseEntity<String> {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId))
    }


    private val logger = KotlinLogging.logger {}

    @GetMapping("/")
//    @Throws(TimeoutException::class)
    fun getLicenses(@PathVariable organizationId: String): List<License> {
        logger.info { "LicenseServiceController organizationId id: $organizationId" }
        val licensesByOrganization = licenseService.getLicensesByOrganization(organizationId)
        logger.info { "return license info ${licensesByOrganization.toString()}" }
        return licensesByOrganization
    }

}