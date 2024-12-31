package com.microservice.chapter8_license_service.model.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL


@JsonInclude(NON_NULL)
data class ResponseWrapper(
    var data: Any? = null,
    var metadata: Any? = null,
    var error: List<ErrorMessage>? = null,
)