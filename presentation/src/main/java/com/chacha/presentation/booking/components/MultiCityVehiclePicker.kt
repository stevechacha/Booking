package com.chacha.presentation.booking.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chacha.presentation.booking.BookingUiState

@Composable
fun MultiCityVehiclePicker(
    bookingUiState: BookingUiState,
    onVehicleChanged:(String) -> Unit,
    onSeatChanged:(String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppTextField(
                value = bookingUiState.passengerCount.toString(),
                title = "Passenger",
                hint = "Select Passenger",
                onValueChange = { passengers ->
                    onSeatChanged(passengers.toString())
                },
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            Spacer(modifier = Modifier.weight(1f))
            AppTextField(
                value = bookingUiState.vehicleType.toString(),
                title = "Vehicle",
                hint = "Select Vehicle",
                onValueChange = { vehicleType ->
                    onVehicleChanged(vehicleType)
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            )

        }

    }


}