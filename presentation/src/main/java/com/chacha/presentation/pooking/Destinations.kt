package com.chacha.presentation.pooking

data class Destinations(
    val from: String = "",
    val to: String = "",
    val departureDate: String = "",
    val returnDate: String = "",
    val passenger: String = "",
    val vehicleType: VehicleTypes = VehicleTypes.BUS,
)
