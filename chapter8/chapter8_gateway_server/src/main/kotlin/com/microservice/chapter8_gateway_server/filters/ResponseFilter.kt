package com.microservice.chapter8_gateway_server.filters

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import reactor.core.publisher.Mono

@Configuration
class ResponseFilter {

    private val logger = KotlinLogging.logger {}

    @Autowired
    private lateinit var filterUtils: FilterUtils

    @Bean
    fun postGlobalFilter(): GlobalFilter {
        return GlobalFilter { exchange, chain ->
            chain.filter(exchange).then(
                Mono.fromRunnable {
                    val requestHeaders: HttpHeaders = exchange.request.headers
                    val correlationId: String = filterUtils.getCorrelationId(requestHeaders).toString()
                    logger.info { "${"Adding the correlation id to the outbound headers. {}"} $correlationId" }
                    exchange.response.headers.add(FilterUtils.CORRELATION_ID, correlationId)
                    logger.info { "${"Completing outgoing request for {}."} ${exchange.request.uri}" }
                }
            )
        }
    }
}