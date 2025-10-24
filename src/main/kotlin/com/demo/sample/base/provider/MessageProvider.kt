package com.demo.sample.base.provider

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component

@Component
class MessageProvider(private val messageSource: MessageSource) {
    fun get(key: String, vararg args: Any?): String {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale())
    }
}