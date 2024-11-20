package com.mircoservice.chapter5_configserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class Chapter5ConfigServerApplication

fun main(args: Array<String>) {
    runApplication<Chapter5ConfigServerApplication>(*args)
}
