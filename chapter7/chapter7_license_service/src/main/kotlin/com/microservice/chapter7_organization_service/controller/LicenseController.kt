package com.microservice.chapter7_organization_service.controller

import com.microservice.chapter7_organization_service.model.License
import com.microservice.chapter7_organization_service.service.LicenseService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeoutException

@RestController
@RequestMapping("v1/organization/{organizationId}/license")
class LicenseController(private val licenseService: LicenseService) {

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
    @Throws(TimeoutException::class)
    fun getLicenses(@PathVariable organizationId: String): List<License> {
        logger.debug { "LicenseServiceController Correlation id: " }
        return licenseService.getLicensesByOrganization(organizationId)
    }

}