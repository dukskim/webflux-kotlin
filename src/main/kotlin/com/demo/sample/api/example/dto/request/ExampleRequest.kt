package com.demo.sample.api.example.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class ExampleRequest(
    @field:NotNull(message = "A2002:이름,2")
//    @field:NotBlank(message = "A2002:이름,2")
    @field:Size(min = 2, message = "A2002:이름,2")
    val name: String?,
    @field:NotNull(message = "A0003")
    @field:NotBlank(message = "A0003")
    val email: String?
)
