package com.demo.sample.base.dto

import com.demo.sample.base.enums.ResultCode

data class ApiResponse<T> (
    val result: ResultCode,
    val code: String? = null,
    val msg: String? = null,
    val data: T? = null
)