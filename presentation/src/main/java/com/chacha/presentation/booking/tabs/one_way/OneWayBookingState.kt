package com.chacha.presentation.booking.tabs.one_way

import com.chacha.presentation.booking.departure_destination.DepartureDestinationItem

data class OneWayBookingState(
    val isLoading: Boolean? = null,
    val departureDestination: String = "",
    val arrivalDestination: String = "",
    val departureDate: String = "",
    val returnDate: String = "",
    val departureTime: String = "",
    val farePrice: String = "",
    val numberOfPassengers: Int = 1,
    val vehicleType: VehicleType? = VehicleType.BUS,
    val seatNumber: String = "",
    val departureDestinationList: List<DepartureDestinationItem> = emptyList(),

    ) {
    val isFormValid: Boolean
        get() = departureDestination.isNotEmpty() &&
                arrivalDestination.isNotEmpty() &&
                departureDate.isNotEmpty() &&
                departureTime.isNotEmpty() &&
                farePrice.isNotEmpty() &&
                numberOfPassengers > 0 &&
                vehicleType != null &&
                seatNumber.isNotEmpty()
}

enum class VehicleType(val type: String) {
    BUS(type = "Bus"),
    TRAIN(type = "Train"),
    FLIGHT(type = "Flight")
}


sealed interface OneWayBookingEvent {
    object GetBookingEvent : OneWayBookingEvent
    object GetDepartureDestinationList : OneWayBookingEvent
    data class OnEventPageChange(val page: Int) : OneWayBookingEvent
    data class OnEventDepartureDestinationChange(val departureDestination: String) : OneWayBookingEvent
    data class OnEventArrivalDestinationChange(val arrivalDestination: String) : OneWayBookingEvent
    data class OnEventDepartureDateChange(val departureDate: String) : OneWayBookingEvent
    data class OnEventDepartureTimeChange(val departureTime: String) : OneWayBookingEvent
    data class OnEventFarePriceChange(val farePrice: String) : OneWayBookingEvent
    data class OnEventBookingChange(val booking: String) : OneWayBookingEvent
    data class OnEventVehicleTypeChange(val vehicleType: VehicleType) : OneWayBookingEvent
}