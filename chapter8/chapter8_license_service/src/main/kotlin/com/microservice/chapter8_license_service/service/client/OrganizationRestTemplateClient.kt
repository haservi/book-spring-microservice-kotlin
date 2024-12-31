package com.microservice.chapter8_license_service.service.client

import com.microservice.chapter8_license_service.model.Organization
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class OrganizationRestTemplateClient(
    @Autowired private val restTemplate: RestTemplate
) {

    fun getOrganization(organizationId: String): Organization? {
        val restExchange = restTemplate.exchange(
            "http://localhost:8081/v1/organization/{organizationId}",
            HttpMethod.GET,
            null,
            Organization::class.java,
            organizationId
        )

        return restExchange.body
    }

}