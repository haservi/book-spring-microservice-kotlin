package com.microservice.chapter5_license.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "example")
data class ServiceConfig(
    var property: String = ""
)