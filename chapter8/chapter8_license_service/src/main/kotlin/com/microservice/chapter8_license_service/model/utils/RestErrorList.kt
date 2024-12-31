package com.microservice.chapter8_license_service.model.utils

import org.springframework.http.HttpStatus

class RestErrorList(
    var status: HttpStatus,
    vararg errors: ErrorMessage,
) : ArrayList<ErrorMessage>() {

    init {
        addAll(errors.asList())
    }

    constructor(status: Int, vararg errors: ErrorMessage) : this(HttpStatus.valueOf(status), *errors)
}