package com.chacha.presentation.booking.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chacha.presentation.booking.BookingUiState
import com.chacha.presentation.common.components.RideCard

@Composable
fun VehicleCardItem(
    bookingUiState: BookingUiState,
    onVehicleChanged:(String)->Unit,
) {
    RideCard {
        AppTextField(
            value = bookingUiState.vehicleType.type.toString(),
            title = "Vehicle",
            hint = "Select Vehicle",
            onValueChange = { vehicleType ->
                onVehicleChanged(vehicleType.toString())
            },
            modifier = Modifier.fillMaxWidth()

        )
    }

}