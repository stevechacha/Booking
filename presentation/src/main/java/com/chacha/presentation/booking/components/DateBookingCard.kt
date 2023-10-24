package com.chacha.presentation.booking.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chacha.presentation.booking.BookingUiState
import com.chacha.presentation.booking.booking_calender.dateFormatterr

@SuppressLint("NewApi")
@Composable
fun DateBookingCard(
    bookingUiState: BookingUiState,
    onDateChanged:(String) -> Unit,
    onTimeChanged:(String) -> Unit,
    onFareChanged:(String) -> Unit,
    onSeatChanged:(String) -> Unit,
) {

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            AppTextField(
                value = dateFormatterr.format(bookingUiState.departureDate).toString(),
                onValueChange = { departureDate->
                    onDateChanged(departureDate)
                },
                title = "Departure Date",
                hint = "Select Date",
                modifier = Modifier.fillMaxWidth()
            )

        }

    }

}

@Composable
@Preview
fun DateBookingCardPreview() {
    DateBookingCard(
        bookingUiState = BookingUiState(),
        onDateChanged = {},
        onTimeChanged = {},
        onFareChanged = {},
        onSeatChanged = {},
    )
}
