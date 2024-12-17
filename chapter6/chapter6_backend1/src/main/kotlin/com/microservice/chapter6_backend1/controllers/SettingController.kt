package com.microservice.chapter6_backend1.controllers

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

    @Value("\${backend1.property:default-value}")
    private lateinit var backend1Property: String

    @GetMapping("/property")
    fun getProperty(): String {
        logger.info { "backend1.property: $backend1Property" }
        return "backend1 -> env: $backend1Property"
    }


}