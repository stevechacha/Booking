package com.chacha.presentation.pooking

sealed interface PookingEvent {
    object From :PookingEvent
    data class To(val to: String): PookingEvent
    data class DepartureDate(val departureDate: String): PookingEvent
    data class ReturnDate(val returnDate: String): PookingEvent
    data class Passenger(val passenger: Int): PookingEvent
    data class VehicleType(val vehicleType: VehicleTypes): PookingEvent
    data class OnSearchQuery(val query: String): PookingEvent
    object Too: PookingEvent
}

enum class VehicleTypes {
    CAR,
    BIKE,
    BUS,
    TRAIN,
    FLIGHT
}