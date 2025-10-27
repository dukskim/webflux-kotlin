package com.demo.sample.base.factory

import com.demo.sample.base.dto.ApiResponse
import com.demo.sample.base.enums.ErrorCode
import com.demo.sample.base.enums.ResultCode
import com.demo.sample.base.provider.MessageProvider
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ApiResponseFactory(private val messageProvider: MessageProvider) {

    fun <T> success(data: T?): ResponseEntity<ApiResponse<T>> =
        ResponseEntity.ok(ApiResponse(result = ResultCode.SUCCESS, code = null, msg = null, data = data))

    fun <T> success(data: T?, msg: String = "성공"): ResponseEntity<ApiResponse<T>> =
        ResponseEntity.ok(ApiResponse(result = ResultCode.SUCCESS, code = null, msg = msg, data = data))

    fun fail(code: ErrorCode): ResponseEntity<ApiResponse<Nothing>> =
        ResponseEntity.status(code.status).body(ApiResponse(result = ResultCode.ERROR, code = code.code, msg = messageProvider.get(code.message), data = null))

    fun fail(code: ErrorCode, args: Array<Any>): ResponseEntity<ApiResponse<Nothing>> =
        ResponseEntity.status(code.status).body(ApiResponse(result = ResultCode.ERROR, code = code.code, msg = messageProvider.get(code.message, *args), data = null))

    fun fail(code: ErrorCode, msg: String): ResponseEntity<ApiResponse<Nothing>> =
        ResponseEntity.status(code.status).body(ApiResponse(result = ResultCode.ERROR, code = code.code, msg = msg, data = null))

    fun fail(code: String): ResponseEntity<ApiResponse<Nothing>> {
        val errorCode = ErrorCode.findByCode(code) ?: ErrorCode.ERR

        return ResponseEntity.status(errorCode.status).body(
            ApiResponse(
                result = ResultCode.ERROR,
                code = code,
                msg = messageProvider.get(errorCode.message),
                data = null
            )
        )
    }

    fun fail(code: String, args: Array<Any>): ResponseEntity<ApiResponse<Nothing>> {
        val errorCode = ErrorCode.findByCode(code) ?: ErrorCode.ERR
        return ResponseEntity.status(errorCode.status).body(
            ApiResponse(
                result = ResultCode.ERROR,
                code = code,
                msg = messageProvider.get(errorCode.message, *args),
                data = null
            )
        )
    }

    fun fail(code: String, msg: String): ResponseEntity<ApiResponse<Nothing>> {
        val errorCode = ErrorCode.findByCode(code) ?: ErrorCode.ERR
        return ResponseEntity.status(errorCode.status).body(
            ApiResponse(
                result = ResultCode.ERROR,
                code = code,
                msg = msg,
                data = null
            )
        )
    }
}