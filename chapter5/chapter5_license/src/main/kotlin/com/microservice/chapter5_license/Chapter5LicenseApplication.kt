package com.microservice.chapter5_license

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.context.annotation.Bean
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@SpringBootApplication
@RefreshScope
class Chapter5LicenseApplication

fun main(args: Array<String>) {
    runApplication<Chapter5LicenseApplication>(*args)
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
