package com.chacha.presentation.booking.departure_destination

sealed class DepartureDestinationEvent{
    object GetDepartureDestinationList: DepartureDestinationEvent()
    data class OnEventDepartureDestinationChange(val departureDestination: String): DepartureDestinationEvent()
    data class SearchDepartureDestination(val searchDepartureDestination: String): DepartureDestinationEvent()

}