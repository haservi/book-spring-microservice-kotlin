package com.microservice.chapter6_backend1

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
class Chapter6Backend1Application

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    runApplication<Chapter6Backend1Application>(*args)
}

@LoadBalanced
@Bean
fun restTemplate(): RestTemplate {
    return RestTemplate()
}
