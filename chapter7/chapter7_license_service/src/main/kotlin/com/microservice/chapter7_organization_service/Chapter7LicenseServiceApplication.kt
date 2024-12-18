package com.microservice.chapter7_organization_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
class Chapter7LicenseServiceApplication

fun main(args: Array<String>) {
    runApplication<Chapter7LicenseServiceApplication>(*args)
}

@Bean
fun localeResolver(): LocaleResolver {
    val localeResolver = SessionLocaleResolver()
    localeResolver.setDefaultLocale(Locale.KOREAN)
    return localeResolver
}

@Bean
fun messageSource(): ResourceBundleMessageSource {
    val messageSource = ResourceBundleMessageSource()
    messageSource.setUseCodeAsDefaultMessage(true)
    messageSource.setBasenames("messages")
    return messageSource
}

@LoadBalanced
@Bean
fun getRestTemplate(): RestTemplate {
    return RestTemplate()
}
