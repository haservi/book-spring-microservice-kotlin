package com.mircoservice.chapter8_config_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class Chapter8ConfigServerApplication

fun main(args: Array<String>) {
    runApplication<Chapter8ConfigServerApplication>(*args)
}
