package com.microservice.chapter7_eureka_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class Chapter7EurekaServerApplication

fun main(args: Array<String>) {
    runApplication<Chapter7EurekaServerApplication>(*args)
}
