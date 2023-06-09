package com.chacha.presentation.trips

data class MyTripState(
    val isLoading: Boolean? = null,
    val error: String? = null,
    val settledPage: Int = 0
)
