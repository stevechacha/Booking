package com.chacha.presentation.pooking

data class PookingState(
    val destination: List<Destinations> = emptyList(),
    val from: String = "",
    val to: String = "",
    val departureDate: String = "",
    val returnDate: String = "",
    val passenger: Int= 1,
    val isLoading: Boolean = false,
    val vehicleTypes: VehicleTypes = VehicleTypes.BUS,
)
