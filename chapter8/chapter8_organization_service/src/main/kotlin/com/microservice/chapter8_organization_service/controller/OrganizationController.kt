package com.microservice.chapter8_organization_service.controller

import com.microservice.chapter8_organization_service.model.Organization
import com.microservice.chapter8_organization_service.service.OrganizationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/organization")
class OrganizationController(
    private val service: OrganizationService
) {

    @GetMapping("/{organizationId}")
    fun getOrganization(@PathVariable organizationId: String): ResponseEntity<Organization> {
        return ResponseEntity.ok(service.findById(organizationId))
    }

    @PutMapping("/{organizationId}")
    fun updateOrganization(
        @PathVariable organizationId: String,
        @RequestBody organization: Organization
    ) {
        service.update(organization)
    }

    @PostMapping
    fun saveOrganization(@RequestBody organization: Organization): ResponseEntity<Organization> {
        return ResponseEntity.ok(service.create(organization))
    }

    @DeleteMapping("/{organizationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOrganization(
        @PathVariable organizationId: String,
        @RequestBody organization: Organization
    ) {
        service.delete(organization)
    }
}