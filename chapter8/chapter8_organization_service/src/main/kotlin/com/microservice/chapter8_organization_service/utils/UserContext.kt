package com.microservice.chapter8_organization_service.utils

import org.springframework.stereotype.Component

@Component
class UserContext {
    companion object {
        const val CORRELATION_ID = "tmx-correlation-id"
        const val AUTH_TOKEN = "tmx-auth-token"
        const val USER_ID = "tmx-user-id"
        const val ORG_ID = "tmx-org-id"
    }

    var correlationId: String = ""
    var authToken: String = ""
    var userId: String = ""
    var organizationId: String = ""
}