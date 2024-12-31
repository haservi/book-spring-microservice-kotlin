package com.microservice.chapter8_organization_service.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@RefreshScope
class SettingController(
) {

    private val logger = KotlinLogging.logger {}

    @Value("\${backend1.property:default-value}")
    private lateinit var property: String

    @GetMapping("/property")
    fun getProperty(): String {
        logger.info { "backend1.property: $property" }
        return "backend1 -> env: $property"
    }
}