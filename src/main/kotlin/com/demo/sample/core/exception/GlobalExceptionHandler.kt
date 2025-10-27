package com.demo.sample.core.exception

import com.demo.sample.base.dto.ApiResponse
import com.demo.sample.base.enums.ErrorCode
import com.demo.sample.base.factory.ApiResponseFactory
import jakarta.validation.ConstraintViolationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException

@RestControllerAdvice
class GlobalExceptionHandler(private val responseFactory: ApiResponseFactory
) {

    // Log
    private val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    /**
     * 사용자 정의 에러
     */
    @ExceptionHandler(ApiException::class)
    fun handleApiException(ex: ApiException): ResponseEntity<ApiResponse<Nothing>> {
        return if (ex.args != null) {
            responseFactory.fail(ex.code, ex.args)
        } else if (ex.msg != null) {
            responseFactory.fail(ex.code, ex.msg)
        } else {
            responseFactory.fail(ex.code)
        }
    }

    /**
     * Exception
     */
    @ExceptionHandler(Exception::class)
    suspend fun handleGenericException(ex: Exception): ResponseEntity<ApiResponse<Nothing>> {
        return responseFactory.fail(ErrorCode.SYSTEM_ERROR)
    }

    /**
     * Valid 체크 (RequestBody, ModelAttribute)
     */
    @ExceptionHandler(WebExchangeBindException::class)
    suspend fun handleBindException(ex: WebExchangeBindException): ResponseEntity<ApiResponse<Nothing>> {
        val firstError = ex.bindingResult.fieldErrors.firstOrNull()

        if (firstError != null) {
            val rawMessage = firstError.defaultMessage ?: ""

            val code = rawMessage.substringBefore(":")
            val argString = rawMessage.substringAfter(":", "")
            val errorCode = ErrorCode.findByCode(code) ?: ErrorCode.BAD_REQUEST

            if (argString.isNotEmpty()) {
                val args: Array<Any> = argString.split(",").map { it.trim() }.toTypedArray()
                return responseFactory.fail(errorCode, args)
            } else {
                return responseFactory.fail(errorCode)
            }
        }

        return responseFactory.fail(ErrorCode.BAD_REQUEST)
    }

    /**
     * Valid 체크 (RequestParam, PathVariable)
     */
    @ExceptionHandler(ConstraintViolationException::class)
    suspend fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<ApiResponse<Nothing>> {
        val firstViolation = ex.constraintViolations.firstOrNull()

        if (firstViolation != null) {
            val rawMessage = firstViolation.message ?: ""

            val code = rawMessage.substringBefore(":")
            val argString = rawMessage.substringAfter(":", "")
            val errorCode = ErrorCode.findByCode(code) ?: ErrorCode.BAD_REQUEST

            return if (argString.isNotEmpty()) {
                val args: Array<Any> = argString.split(",").map { it.trim() }.toTypedArray()
                responseFactory.fail(errorCode, args)
            } else {
                responseFactory.fail(errorCode)
            }
        }

        return responseFactory.fail(ErrorCode.BAD_REQUEST)
    }

}
