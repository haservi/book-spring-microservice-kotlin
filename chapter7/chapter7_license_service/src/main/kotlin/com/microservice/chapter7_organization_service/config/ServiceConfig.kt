package com.microservice.chapter7_organization_service.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "example")
data class ServiceConfig(
    var property: String = ""
)