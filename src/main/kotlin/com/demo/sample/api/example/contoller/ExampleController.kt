package com.demo.sample.api.example.contoller

import com.demo.sample.api.example.dto.request.ExampleRequest
import com.demo.sample.api.example.dto.response.ExampleResponse
import com.demo.sample.api.example.service.ExampleService
import com.demo.sample.base.dto.ApiResponse
import com.demo.sample.base.enums.ErrorCode
import com.demo.sample.base.factory.ApiResponseFactory
import com.demo.sample.core.exception.ApiException
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
    suspend fun exampleSuccess(): ResponseEntity<ApiResponse<List<ExampleResponse>>> {
        return apiResponseFactory.success(exampleService.findAll())
    }
    @GetMapping("/error")
    suspend fun exampleError(): ResponseEntity<ApiResponse<Nothing>> {
        return apiResponseFactory.fail(ErrorCode.SYSTEM_ERROR)
    }

    @GetMapping("/exception1")
    suspend fun exampleException(): ResponseEntity<ApiResponse<List<ExampleResponse>>> {

        if (true) throw ApiException(ErrorCode.SESSION_EXPIRED)

        return apiResponseFactory.success(exampleService.findAll())
    }
    @GetMapping("/exception2")
    suspend fun exampleException2(): ResponseEntity<ApiResponse<List<ExampleResponse>>> {

        if (true) throw ApiException(ErrorCode.NOT_ENOUGH_CHARACTERS_SIZE, args = arrayOf("1", "2"))

        return apiResponseFactory.success(exampleService.findAll())
    }

    @PostMapping("/valid-post")
    suspend fun exampleValid(@RequestBody @Valid request: ExampleRequest): ResponseEntity<ApiResponse<List<ExampleResponse>>> {
        return apiResponseFactory.success(exampleService.findAll())
    }
}