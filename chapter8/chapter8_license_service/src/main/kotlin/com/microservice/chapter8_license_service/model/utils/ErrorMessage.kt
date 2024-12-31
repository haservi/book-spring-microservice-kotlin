package com.microservice.chapter8_license_service.model.utils

data class ErrorMessage(
    var message: String,
    var code: String = "",
    var detail: String = "",
) {
    constructor(message: String, detail: String) : this(message, "", detail)
    constructor(message: String) : this(message, "", "")
}