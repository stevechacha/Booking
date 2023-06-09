package com.chacha.presentation.booking

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookingViewModel : ViewModel() {

    private val _bookingState = MutableStateFlow(BookingState())
    val bookingState: StateFlow<BookingState> = _bookingState

    fun onTriggerEvent(event: BookingEvent) {
        when (event) {
            is BookingEvent.OnClick -> {

            }
            is BookingEvent.OnClickWithParam -> {

            }
            is BookingEvent.OnEventPageChange -> {
                _bookingState.update { it.copy(settledPage = event.settledPage) }
            }
        }
    }
}