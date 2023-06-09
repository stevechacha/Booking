package com.chacha.presentation.booking.departure_destination

data class DepartureDestinationState(
    val isLoading: Boolean = false,
    val departureDestination: String = "",
    val departureDestinationList: List<DepartureDestinationItem> = listOf(),
    val searchDepartureDestination : String = ""

)
