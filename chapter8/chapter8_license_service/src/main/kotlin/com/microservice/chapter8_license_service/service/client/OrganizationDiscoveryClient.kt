package com.microservice.chapter8_license_service.service.client

import com.microservice.chapter8_license_service.model.Organization
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class OrganizationDiscoveryClient(
    @Autowired private val discoveryClient: DiscoveryClient
) {

    fun getOrganization(organizationId: String): Organization? {
        val restTemplate = RestTemplate()
        val instances: List<ServiceInstance> = discoveryClient.getInstances("organization-service")

        if (instances.isEmpty()) return null

        val serviceUri = "${instances[0].uri}/v1/organization/$organizationId"
        val restExchange: ResponseEntity<Organization> = restTemplate.exchange(
            serviceUri, HttpMethod.GET, null, Organization::class.java, organizationId
        )

        return restExchange.body
    }
}