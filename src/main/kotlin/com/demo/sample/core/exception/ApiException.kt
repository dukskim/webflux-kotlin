package com.demo.sample.core.exception

import com.demo.sample.base.enums.ErrorCode

class ApiException(
    val code: ErrorCode,
    val msg: String? = null,
    val args: Array<Any>? = null,
) : RuntimeException()