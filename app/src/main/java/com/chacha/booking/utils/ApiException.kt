package com.chacha.booking.utils

class ApiException(
    val statusCode: Int = 0, val statusMessage: String
) : Throwable(statusMessage)