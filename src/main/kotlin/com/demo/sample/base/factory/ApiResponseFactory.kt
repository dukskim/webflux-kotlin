package com.demo.sample.base.factory

import com.demo.sample.base.dto.ApiResponse
import com.demo.sample.base.enums.ErrorCode
import com.demo.sample.base.enums.ResultCode

object ApiResponseFactory {

    fun <T> success(data: T): ApiResponse<T> =
        ApiResponse(result = ResultCode.SUCCESS, code = null, msg = null, data = data)

    fun <T> success(data: T, msg: String = "성공"): ApiResponse<T> =
        ApiResponse(result = ResultCode.SUCCESS, code = null, msg = msg, data = data)

    fun fail(code: ErrorCode): ApiResponse<Nothing> =
        ApiResponse(result = ResultCode.ERROR, code = code.code, msg = code.message, data = null)

    fun fail(code: ErrorCode, msg: String): ApiResponse<Nothing> =
        ApiResponse(result = ResultCode.ERROR, code = code.code, msg = msg, data = null)


}