package com.microservice.chapter1

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hello")
class HelloController {

    @GetMapping("/{firstName}")
    fun helloGet(@PathVariable firstName: String, @RequestParam lastName: String): String {
        return """{"message":"Hello $firstName $lastName"}"""
    }

    @PostMapping
    fun helloPost(@RequestBody request: HelloRequest): String {
        return """{"message":"Hello ${request.firstName} ${request.lastName}}"""
    }

}