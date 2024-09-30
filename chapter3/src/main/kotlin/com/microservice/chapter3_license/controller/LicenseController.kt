package com.microservice.chapter3_license.controller

import com.microservice.chapter3_license.model.License
import com.microservice.chapter3_license.service.LicenseService
import org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

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
            linkTo(methodOn(LicenseController::class.java).getLicense(organizationId, license.licenseId)).withSelfRel(),
            linkTo(
                methodOn(LicenseController::class.java).createLicense(
                    organizationId,
                    license,
                    null,
                )
            ).withRel("createLicense"),
            linkTo(
                methodOn(LicenseController::class.java).updateLicense(
                    organizationId,
                    license,
                    null,
                )
            ).withRel("updateLicense"),
            linkTo(
                methodOn(LicenseController::class.java).deleteLicense(
                    organizationId,
                    license.licenseId,
                    null,
                )
            ).withRel("deleteLicense")
        )
        return ResponseEntity.ok(license)
    }

    @PutMapping
    fun updateLicense(
        @PathVariable organizationId: String,
        @RequestBody request: License,
        @RequestHeader(value = "Accept-Language", required = false) locale: Locale?,
    ): ResponseEntity<String> {
        val response = licenseService.updateLicense(request, organizationId, locale ?: Locale.getDefault())
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun createLicense(
        @PathVariable organizationId: String,
        @RequestBody request: License,
        @RequestHeader(value = "Accept-Language", required = false) locale: Locale?,
    ): ResponseEntity<String> {
        val response = licenseService.createLicense(request, organizationId, locale ?: Locale.getDefault())
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{licenseId}")
    fun deleteLicense(
        @PathVariable organizationId: String,
        @PathVariable licenseId: String,
        @RequestHeader(value = "Accept-Language", required = false) locale: Locale?
    ): ResponseEntity<String> {
        val response = licenseService.deleteLicense(licenseId, organizationId, locale ?: Locale.getDefault())
        return ResponseEntity.ok(response)
    }
}