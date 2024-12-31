package com.microservice.chapter8_organization_service.utils

import org.springframework.util.Assert

object UserContextHolder {
    private val userContext: ThreadLocal<UserContext> = ThreadLocal()

    fun getContext(): UserContext {
        var context = userContext.get()
        if (context == null) {
            context = createEmptyContext()
            userContext.set(context)
        }
        return context
    }

    fun setContext(context: UserContext) {
        Assert.notNull(context, "context는 null일 수 없습니다.")
        userContext.set(context)
    }

    private fun createEmptyContext(): UserContext? {
        return UserContext()
    }
}