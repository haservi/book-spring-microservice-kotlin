package com.microservice.chapter7_organization_service.service

import com.microservice.chapter7_organization_service.model.Organization
import com.microservice.chapter7_organization_service.repository.OrganizationRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrganizationService(
    private val repository: OrganizationRepository
) {

    private val logger = KotlinLogging.logger {}

    fun findById(organizationId: String): Organization? {
        return repository.findById(organizationId).orElse(null)
    }

    fun create(organization: Organization): Organization {
        organization.id = UUID.randomUUID().toString()
        return repository.save(organization)
    }

    fun update(organization: Organization) {
        repository.save(organization)
    }

    fun delete(organization: Organization) {
        repository.deleteById(organization.id)
    }

}