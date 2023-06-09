package com.chacha.presentation.booking.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingState
import com.chacha.presentation.common.theme.Border

@Composable
fun MultiCityVehiclePicker(
    oneWayBookingState: OneWayBookingState,
    onVehicleChanged:(String) -> Unit,
    onSeatChanged:(String) -> Unit,
    onBookClick:() -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        border = BorderStroke(1.dp, Border),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppTextField(
                value = oneWayBookingState.numberOfPassengers.toString(),
                title = "Passenger",
                hint = "Select Passenger",
                onValueChange = { passengers ->
                    onSeatChanged(passengers)
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .clickable(onClick = {})
            )
            Spacer(modifier = Modifier.weight(1f))
            AppTextField(
                value = oneWayBookingState.vehicleType.toString(),
                title = "Vehicle",
                hint = "Select Vehicle",
                onValueChange = { vehicleType ->
                    onVehicleChanged(vehicleType)
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .clickable(onClick = {})
            )

        }

    }


}