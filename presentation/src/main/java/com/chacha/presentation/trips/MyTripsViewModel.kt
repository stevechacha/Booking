package com.chacha.presentation.trips

import androidx.lifecycle.ViewModel
import com.chacha.presentation.booking.BookingEvent
import com.chacha.presentation.booking.BookingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyTripsViewModel : ViewModel() {
    private val _myTripState = MutableStateFlow(MyTripState())
    val myTripState: StateFlow<MyTripState> = _myTripState

    fun onTriggerEvent(event: BookingEvent) {
        when (event) {
            is BookingEvent.OnClick -> {

            }
            is BookingEvent.OnClickWithParam -> {

            }
            is BookingEvent.OnEventPageChange -> {
                _myTripState.update { it.copy(settledPage = event.settledPage) }
            }
        }
    }
}