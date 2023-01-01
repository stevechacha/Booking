package com.chacha.booking.core.utils

class ApiException(
    val statusCode: Int = 0, val statusMessage: String
) : Throwable(statusMessage)