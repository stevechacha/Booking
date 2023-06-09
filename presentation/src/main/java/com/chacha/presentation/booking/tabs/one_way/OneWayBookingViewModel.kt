package com.chacha.presentation.booking.tabs.one_way

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class OneWayBookingViewModel : ViewModel() {

    val state = MutableStateFlow(OneWayBookingState())

    fun onEvent(event: OneWayBookingEvent) {
        when (event) {
            OneWayBookingEvent.GetBookingEvent -> {
                state.update { it.copy() }
            }
            is OneWayBookingEvent.OnEventArrivalDestinationChange -> {
                state.update { it.copy(arrivalDestination =  event.arrivalDestination) }
            }
            is OneWayBookingEvent.OnEventBookingChange -> {
                state.update { it.copy() }
            }
            is OneWayBookingEvent.OnEventDepartureDateChange -> {
                state.update { it.copy(departureDate =  event.departureDate) }
            }
            is OneWayBookingEvent.OnEventDepartureDestinationChange -> {
                state.update { it.copy(departureDestination =  event.departureDestination) }
            }

            is OneWayBookingEvent.OnEventDepartureTimeChange -> {
                state.update { it.copy(departureTime =  event.departureTime) }
            }
            is OneWayBookingEvent.OnEventFarePriceChange -> {
                state.update { it.copy(farePrice =  event.farePrice) }
            }
            is OneWayBookingEvent.OnEventPageChange -> {
                state.update { it.copy() }
            }
            is OneWayBookingEvent.OnEventVehicleTypeChange -> {
                state.update { it.copy(vehicleType =  event.vehicleType) }
            }

            OneWayBookingEvent.GetDepartureDestinationList -> {
                state.update { it.copy(isLoading = false) }
            }
        }
    }
}