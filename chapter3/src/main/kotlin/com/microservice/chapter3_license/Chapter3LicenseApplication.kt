package com.microservice.chapter3_license

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@SpringBootApplication
class Chapter3LicenseApplication

fun main(args: Array<String>) {
    runApplication<Chapter3LicenseApplication>(*args)
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
