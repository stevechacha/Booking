package com.chacha.presentation.trips

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyTripsViewModel : ViewModel() {
    private val _myTripState = MutableStateFlow(MyTripState())
    val myTripState: StateFlow<MyTripState> = _myTripState

    fun onTriggerEvent(event: MyTripEvent) {
        when (event) {
            is MyTripEvent.OnClick -> {

            }
            is MyTripEvent.OnClickWithParam -> {

            }
            is MyTripEvent.OnEventPageChange -> {
                _myTripState.update { it.copy(settledPage = event.settledPage) }
            }
        }
    }
}