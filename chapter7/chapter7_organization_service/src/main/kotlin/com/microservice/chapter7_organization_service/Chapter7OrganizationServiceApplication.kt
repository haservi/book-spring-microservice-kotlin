package com.microservice.chapter7_organization_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.context.config.annotation.RefreshScope

@SpringBootApplication
@RefreshScope
class Chapter7OrganizationServiceApplication

fun main(args: Array<String>) {
    runApplication<Chapter7OrganizationServiceApplication>(*args)
}