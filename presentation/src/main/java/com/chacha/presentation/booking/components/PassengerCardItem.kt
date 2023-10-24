package com.chacha.presentation.booking.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chacha.presentation.booking.BookingUiState
import com.chacha.presentation.common.components.RideCard

@Composable
fun PassengerCardItem(
    bookingUiState: BookingUiState,
    onPassengerNumberChanged:(String)->Unit,
) {
    RideCard {
        AppTextField(
            value = bookingUiState.passengerCount.toString(),
            title = "Passenger",
            hint = "Select Passenger",
            onValueChange = { passengers ->
                onPassengerNumberChanged(passengers.toString())
            },
            modifier = Modifier.fillMaxWidth()

        )
    }

}