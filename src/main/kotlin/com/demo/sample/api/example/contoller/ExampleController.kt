package com.demo.sample.api.example.contoller

import com.demo.sample.api.example.dto.response.ExampleResponse
import com.demo.sample.api.example.service.ExampleService
import com.demo.sample.base.dto.ApiResponse
import com.demo.sample.base.enums.ErrorCode
import com.demo.sample.base.factory.ApiResponseFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sample/example")
class ExampleController(private val exampleService: ExampleService, private val apiResponseFactory: ApiResponseFactory) {

    @GetMapping("/data")
    suspend fun findAll(): List<ExampleResponse> {
        return exampleService.findAll()
    }
    @GetMapping("/success")
    suspend fun exampleSuccess(): ApiResponse<List<ExampleResponse>> {
        return apiResponseFactory.success(exampleService.findAll())
    }
    @GetMapping("/error")
    suspend fun exampleError(): ApiResponse<Nothing> {
        return apiResponseFactory.fail(ErrorCode.SYSTEM_ERROR)
    }
}