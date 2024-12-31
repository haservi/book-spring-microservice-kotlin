package com.microservice.chapter8_organization_service.utils

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest

class UserContextFilter : Filter {
    private val logger = KotlinLogging.logger {}

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val httpServletRequest = servletRequest as HttpServletRequest

        UserContextHolder.getContext().apply {
            correlationId = httpServletRequest.getHeader(UserContext.CORRELATION_ID)
            userId = httpServletRequest.getHeader(UserContext.USER_ID)
            authToken = httpServletRequest.getHeader(UserContext.AUTH_TOKEN)
            organizationId = httpServletRequest.getHeader(UserContext.ORG_ID)
        }

        logger.debug { "Organization Service Incoming Correlation id: ${UserContextHolder.getContext().correlationId}" }
    }
}