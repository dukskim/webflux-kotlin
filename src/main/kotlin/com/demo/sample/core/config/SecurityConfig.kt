package com.demo.sample.core.config

import com.demo.sample.base.properties.CorsProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity // AOP 방식으로 메서드 호출 전에 권한 검사 가능(@PreAuthorize, @PostAuthorize, @Secured, @RolesAllowed)
class SecurityConfig (
    private val corsProperties: CorsProperties
) {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .cors { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .authorizeExchange {
                it.pathMatchers("/**").permitAll()
                it.anyExchange().authenticated()
            }
            .build()
    }


// @EnableReactiveMethodSecurity 예시
//    @Service
//    class ExampleService {
//
//        @PreAuthorize("hasRole('ADMIN')")
//        suspend fun deleteExample(id: Long) {
//            // 관리자만 삭제 가능
//        }
//    }


    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.allowedOrigins = corsProperties.allowedOrigins // listOf("*")
        config.allowedMethods = corsProperties.allowedMethods // listOf("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        config.allowedHeaders = corsProperties.allowedHeaders // listOf("*")
        config.allowCredentials = corsProperties.allowCredentials // false

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }


}