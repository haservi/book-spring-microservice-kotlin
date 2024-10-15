package com.microservice.chapter5_license.repository

import com.microservice.chapter5_license.model.License
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LicenseRepository : CrudRepository<License, String> {
    fun findByOrganizationId(organizationId: String): List<License>
    fun findByOrganizationIdAndLicenseId(organizationId: String, licenseId: String): License?
}