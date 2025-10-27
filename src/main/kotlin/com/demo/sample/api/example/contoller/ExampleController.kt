package com.demo.sample.api.example.contoller

import com.demo.sample.api.example.dto.request.ExampleRequest
import com.demo.sample.api.example.dto.response.ExampleResponse
import com.demo.sample.api.example.service.ExampleService
import com.demo.sample.base.dto.ApiResponse
import com.demo.sample.base.enums.ErrorCode
import com.demo.sample.base.factory.ApiResponseFactory
import com.demo.sample.core.exception.ApiException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Example", description = "예제 관련 API")
@Validated  // Get Method 의 RequestParam 검증 시 사용 ->> @NotBlank(message = "A2001:aaa")@RequestParam(value = "aaa", required = false) aaa: String
@RestController
@RequestMapping("/api/sample/example")
class ExampleController(private val exampleService: ExampleService, private val apiResponseFactory: ApiResponseFactory) {

    @Operation(
        summary = "예제 목록 조회",
        description = "모든 예제 데이터를 조회합니다"
    )
    @GetMapping("/data")
    suspend fun findAll(): List<ExampleResponse> {
        return exampleService.findAll()
    }

    @Operation(
        summary = "성공코드 및 데이터 응답",
        description = "성공코드 및 데이터 조회"
    )
    @GetMapping("/success")
    suspend fun exampleSuccess(): ResponseEntity<ApiResponse<List<ExampleResponse>>> {
        return apiResponseFactory.success(exampleService.findAll())
    }

    @Operation(
        summary = "실패코드 응답",
        description = "실패코드 및 데이터 조회"
    )
    @GetMapping("/error")
    suspend fun exampleError(): ResponseEntity<ApiResponse<Nothing>> {
        return apiResponseFactory.fail(ErrorCode.SYSTEM_ERROR)
    }

    @Operation(
        summary = "예외로 실패응답",
        description = "예외로 실패응답"
    )
    @GetMapping("/exception1")
    suspend fun exampleException(): ResponseEntity<ApiResponse<List<ExampleResponse>>> {

        if (true) throw ApiException(ErrorCode.SESSION_EXPIRED)

        return apiResponseFactory.success(exampleService.findAll())
    }

    @Operation(
        summary = "예외로 실패응답 - 메시지 인자 처리",
        description = "예외로 실패응답 - 메시지 인자 처리"
    )
    @GetMapping("/exception2")
    suspend fun exampleException2(): ResponseEntity<ApiResponse<List<ExampleResponse>>> {

        if (true) throw ApiException(ErrorCode.NOT_ENOUGH_CHARACTERS_SIZE, args = arrayOf("1", "2"))

        return apiResponseFactory.success(exampleService.findAll())
    }

    @Operation(
        summary = "Valid 테스트 - Request Body",
        description = "Valid 테스트 - Request Body"
    )
    @PostMapping("/valid-post")
    suspend fun exampleValid(@RequestBody @Valid request: ExampleRequest): ResponseEntity<ApiResponse<List<ExampleResponse>>> {
        return apiResponseFactory.success(exampleService.findAll())
    }

    @Operation(
        summary = "Valid 테스트 - Request Param",
        description = "Valid 테스트 - Request Param"
    )
    @GetMapping("/valid-get")
    suspend fun exampleValid(
        @Parameter(description = "아이디")
        @RequestParam(value = "id", required = true)
        @NotNull(message = "A2001:아이디")
        @NotBlank(message = "A2001:아이디")
        @Size(min = 2, message = "A2002:아이디,2")
        id: String?
    ): ResponseEntity<ApiResponse<List<ExampleResponse>>> {
        return apiResponseFactory.success(exampleService.findAll())
    }
}