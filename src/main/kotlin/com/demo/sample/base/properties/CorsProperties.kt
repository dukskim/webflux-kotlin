package com.demo.sample.base.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "cors")
class CorsProperties {
    var allowedOrigins: List<String> = listOf()
    var allowedMethods: List<String> = listOf()
    var allowedHeaders: List<String> = listOf()
    var allowCredentials: Boolean = false

}