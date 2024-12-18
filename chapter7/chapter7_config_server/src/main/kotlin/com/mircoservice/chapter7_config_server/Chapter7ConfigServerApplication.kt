package com.mircoservice.chapter7_config_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class Chapter7ConfigServerApplication

fun main(args: Array<String>) {
    runApplication<Chapter7ConfigServerApplication>(*args)
}
