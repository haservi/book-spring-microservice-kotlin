package com.microservice.chapter8_organization_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.context.config.annotation.RefreshScope

@SpringBootApplication
@RefreshScope
class Chapter8OrganizationServiceApplication

fun main(args: Array<String>) {
    runApplication<Chapter8OrganizationServiceApplication>(*args)
}
