package com.microservice.chapter6_backend2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
class Chapter6Backend2Application

fun main(args: Array<String>) {
    runApplication<Chapter6Backend2Application>(*args)
}
