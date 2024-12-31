package com.microservice.chapter8_gateway_server.filters

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Order(1)
@Component
class TrackingFilter(
    private val filterUtils: FilterUtils
) : GlobalFilter {

    private val logger = KotlinLogging.logger {}

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val requestHeaders = exchange.request.headers

        if (isCorrelationIdPresent(requestHeaders)) {
            logger.info { "tmx-correlation-id found in tracking filter: ${filterUtils.getCorrelationId(requestHeaders)}" }
        } else {
            val correlationId = generateCorrelationId()
            val updatedExchange = filterUtils.setCorrelationId(exchange, correlationId)
            logger.info { "tmx-correlation-id generated in tracking filter: $correlationId" }
            return chain.filter(updatedExchange)
        }

        return chain.filter(exchange)
    }

    private fun isCorrelationIdPresent(requestHeaders: HttpHeaders): Boolean {
        return filterUtils.getCorrelationId(requestHeaders) != null
    }

    private fun generateCorrelationId(): String {
        return java.util.UUID.randomUUID().toString()
    }
}