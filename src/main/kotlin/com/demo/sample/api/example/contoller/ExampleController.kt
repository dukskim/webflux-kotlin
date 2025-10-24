package com.demo.sample.api.example.contoller

import com.demo.sample.api.example.dto.response.ExampleResponse
import com.demo.sample.api.example.service.ExampleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sample/example")
class ExampleController(private val exampleService: ExampleService) {

    @GetMapping
    suspend fun findAll(): List<ExampleResponse> {
        return exampleService.findAll()
    }
}