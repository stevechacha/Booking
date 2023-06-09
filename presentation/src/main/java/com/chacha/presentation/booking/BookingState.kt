package com.chacha.presentation.booking

data class BookingState(
    val isLoading: Boolean? = null,
    val error: String? = null,
    val settledPage: Int = 0,

)

