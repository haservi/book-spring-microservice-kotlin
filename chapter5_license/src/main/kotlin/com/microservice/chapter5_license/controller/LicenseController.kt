package com.microservice.chapter5_license.controller

import com.microservice.chapter5_license.model.License
import com.microservice.chapter5_license.service.LicenseService
import org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/organization/{organizationId}/license")
class LicenseController(private val licenseService: LicenseService) {

    @GetMapping("/{licenseId}")
    fun getLicense(
        @PathVariable organizationId: String,
        @PathVariable licenseId: String
    ): ResponseEntity<License> {
        val license = licenseService.getLicense(licenseId, organizationId)
        license.add(
            linkTo(
                methodOn(LicenseController::class.java).getLicense(organizationId, license.licenseId)
            ).withSelfRel(),

            linkTo(
                methodOn(LicenseController::class.java).createLicense(license)
            ).withRel("createLicense"),

            linkTo(
                methodOn(LicenseController::class.java).updateLicense(
                    license
                )
            ).withRel("updateLicense"),

            linkTo(
                methodOn(LicenseController::class.java).deleteLicense(
                    license.licenseId,
                )
            ).withRel("deleteLicense")
        )
        return ResponseEntity.ok(license)
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
}