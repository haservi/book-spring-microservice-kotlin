package com.microservice.chapter6_backend2.controllers

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SettingController(
) {

    private val logger = KotlinLogging.logger {}

    @Value("\${backend2.property:default-value}")
    private lateinit var backend2Property: String

    @GetMapping("/property")
    fun getProperty(): String {
        logger.info { "backend2.property: $backend2Property" }
        return "backend2 -> env: $backend2Property"
    }
}