package com.demo.sample.api.example.repository

import com.demo.sample.api.example.model.entity.Example
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ExampleRepository: ReactiveCrudRepository<Example, Long> {
}