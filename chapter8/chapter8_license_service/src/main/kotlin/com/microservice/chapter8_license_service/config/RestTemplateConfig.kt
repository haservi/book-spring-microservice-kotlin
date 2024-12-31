package com.microservice.chapter8_license_service.config

import com.microservice.chapter8_license_service.utils.UserContextInterceptor
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    @LoadBalanced
    @Bean
    fun restTemplate(): RestTemplate {
        val template = RestTemplate()
        val interceptors = template.interceptors?.toMutableList() ?: mutableListOf()
        interceptors.add(UserContextInterceptor())
        template.interceptors = interceptors
        return template
    }
}