package com.microservice.chapter8_eureka_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class Chapter8EurekaServerApplication

fun main(args: Array<String>) {
    runApplication<Chapter8EurekaServerApplication>(*args)
}
