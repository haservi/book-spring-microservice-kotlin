package com.microservice.chapter8_license_service.utils

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

@Component
class UserContextFilter : Filter {
    private val logger = KotlinLogging.logger {}

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val httpServletRequest = servletRequest as HttpServletRequest

        with(UserContextHolder.getContext()) {
            correlationId = httpServletRequest.getHeader(UserContext.CORRELATION_ID)
            userId = "userId"
            authToken = "authToken"
            organizationId = "org"
        }
//        UserContextHolder.getContext().apply {
//            correlationId = httpServletRequest.getHeader(UserContext.CORRELATION_ID)
//            userId = httpServletRequest.getHeader(UserContext.USER_ID)
//            authToken = httpServletRequest.getHeader(UserContext.AUTH_TOKEN)
//            organizationId = httpServletRequest.getHeader(UserContext.ORGANIZATION_ID)
//        }

        logger.info { "UserContextFilter Correlation id: ${UserContextHolder.getContext().correlationId}" }

        filterChain.doFilter(httpServletRequest, servletResponse)
    }


    override fun init(filterConfig: FilterConfig?) {
        logger.info { "init filter" }
        // No initialization required
    }

    override fun destroy() {
        logger.info { "destroy filter" }
        // No cleanup required
    }
}
