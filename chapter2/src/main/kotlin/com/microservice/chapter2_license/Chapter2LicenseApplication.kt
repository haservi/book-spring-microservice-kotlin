package com.microservice.chapter2_license

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@RequestMapping("hello")
class Chapter2LicenseApplication

fun main(args: Array<String>) {
    runApplication<Chapter2LicenseApplication>(*args)
}
