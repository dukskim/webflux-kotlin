package com.demo.sample.api.example.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("example")
data class Example(
    @Id
    val id: Long? = null,

    @Column(value = "example_title")
    val exampleTitle: String
)
