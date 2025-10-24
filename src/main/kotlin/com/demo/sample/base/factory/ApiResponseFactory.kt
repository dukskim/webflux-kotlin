package com.demo.sample.base.factory

import com.demo.sample.base.dto.ApiResponse
import com.demo.sample.base.enums.ErrorCode
import com.demo.sample.base.enums.ResultCode
import com.demo.sample.base.provider.MessageProvider
import org.springframework.stereotype.Component

@Component
class ApiResponseFactory(private val messageProvider: MessageProvider) {

    fun <T> success(data: T): ApiResponse<T> =
        ApiResponse(result = ResultCode.SUCCESS, code = null, msg = null, data = data)

    fun <T> success(data: T, msg: String = "성공"): ApiResponse<T> =
        ApiResponse(result = ResultCode.SUCCESS, code = null, msg = msg, data = data)

    fun fail(code: ErrorCode): ApiResponse<Nothing> =
        ApiResponse(result = ResultCode.ERROR, code = code.code, msg = messageProvider.get(code.message), data = null)

    fun fail(code: ErrorCode, args: Array<String>): ApiResponse<Nothing> =
        ApiResponse(result = ResultCode.ERROR, code = code.code, msg = messageProvider.get(code.message, args), data = null)

    fun fail(code: ErrorCode, msg: String): ApiResponse<Nothing> =
        ApiResponse(result = ResultCode.ERROR, code = code.code, msg = msg, data = null)


}