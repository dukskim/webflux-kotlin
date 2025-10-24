package com.demo.sample.api.example.service

import com.demo.sample.api.example.dto.response.ExampleResponse
import org.springframework.stereotype.Service

@Service
class ExampleService() {

    suspend fun findAll(): List<ExampleResponse> {
        val list1 = ExampleResponse(id = 1L, content = "Hello1")
        val list2 = ExampleResponse(id = 2L, content = "Hello2")
        return listOf(list1, list2)
    }

}