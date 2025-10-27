package com.demo.sample.core.config

import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun publicApi(): GroupedOpenApi =
        GroupedOpenApi.builder()
            .group("example-api")
            .pathsToMatch("/**")
            .build()

    @Bean
    fun apiInfo(): Info =
        Info()
            .title("Demo API")
            .description("샘플 프로젝트용 Swagger 문서")
            .version("v1.0.0")

}