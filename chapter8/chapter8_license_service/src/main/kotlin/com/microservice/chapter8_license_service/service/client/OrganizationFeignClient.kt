package com.microservice.chapter8_license_service.service.client

import com.microservice.chapter8_license_service.model.Organization
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("organization-service")
fun interface OrganizationFeignClient {

    @GetMapping("v1/organization/{organizationId}", consumes = ["application/json"])
    fun getOrganization(@PathVariable("organizationId") organizationId: String): Organization
}