package com.microservice.chapter7_organization_service.repository

import com.microservice.chapter7_organization_service.model.Organization
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrganizationRepository : CrudRepository<Organization, String> {
    override fun findById(organization: String): Optional<Organization>
}