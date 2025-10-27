package com.demo.sample.base.enums

import org.springframework.http.HttpStatus

enum class ErrorCode(val code: String, val status: HttpStatus, val message: String) {
    // Error Default
    SYSTEM_ERROR                            ("A0001", HttpStatus.OK, "error.A0001"),
    SESSION_EXPIRED                         ("A0002", HttpStatus.OK, "error.A0002"),
    BAD_REQUEST                             ("A0003", HttpStatus.OK, "error.A0003"),
    SYSTEM_ERROR_ADM                        ("A1001", HttpStatus.OK, "error.A1001"),
    NOT_SUPPORTED                           ("A1002", HttpStatus.OK, "error.A1002"),

    // Valid
    REQUIRED_VALUE                          ("A2001", HttpStatus.OK, "error.A2001"),
    NOT_ENOUGH_CHARACTERS_SIZE              ("A2002", HttpStatus.OK, "error.A2002"),
    INVALID_LENGTH_MAX                      ("A2003", HttpStatus.OK, "error.A2003"),
    INVALID_NICKNAME_FORMAT                 ("A2004", HttpStatus.OK, "error.A2004"),
    INVALID_LOGIN_ID_FORMAT                 ("A2005", HttpStatus.OK, "error.A2005"),
    INVALID_PASSWORD_FORMAT                 ("A2006", HttpStatus.OK, "error.A2006"),

    // token
    INVALID_JWT_TOKEN                       ("A3001", HttpStatus.OK, "error.A3001"),
    EXPIRED_JWT_TOKEN                       ("A3002", HttpStatus.OK, "error.A3002"),
    UNSUPPORTED_JWT_TOKEN                   ("A3003", HttpStatus.OK, "error.A3003"),
    EMPTY_JWT_TOKEN                         ("A3004", HttpStatus.OK, "error.A3004"),
    JWT_PARSE_ERROR                         ("A3005", HttpStatus.OK, "error.A3005"),

    ERR                                     ("", HttpStatus.INTERNAL_SERVER_ERROR, "");


//    companion object {
//        fun findByCode(code: String): ErrorCode? =
//            ErrorCode.entries.find { it.code == code }
//    }
    companion object {
        private val codeMap = ErrorCode.entries.associateBy { it.code }
        fun findByCode(code: String): ErrorCode? = codeMap[code]
    }

}