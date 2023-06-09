package com.chacha.presentation.booking.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingState
import com.chacha.presentation.common.theme.Border

@Composable
fun ReturnDateBookingCard(
    oneWayBookingState: OneWayBookingState,
    onDepartureDateChanged:(String)->Unit,
    onReturnDateChanged:(String)->Unit,
    onPassengerNumberChanged:(Int)->Unit,
    onVehicleChanged:(String)->Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(1.dp, Border),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(12.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppTextField(
                    value = oneWayBookingState.departureDate,
                    onValueChange = { departureDate->
                        onDepartureDateChanged(departureDate)
                    },
                    title = "Departure",
                    hint = "Select ",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clickable(onClick = {})
                )

                AppTextField(
                    value = oneWayBookingState.departureDate,
                    onValueChange = { departureDate->
                        onDepartureDateChanged(departureDate)
                    },
                    title = "Returns ",
                    hint = "Select ",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clickable(onClick = {})
                )

            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppTextField(
                    value = oneWayBookingState.numberOfPassengers.toString(),
                    title = "Passenger",
                    hint = "Select Passenger",
                    onValueChange = { passengers ->
                        onPassengerNumberChanged(passengers.toInt())
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

}

@Composable
@Preview
fun ReturningDatePreview() {
    ReturnDateBookingCard(
        oneWayBookingState = OneWayBookingState(),
        onDepartureDateChanged ={} ,
        onReturnDateChanged = {} ,
        onPassengerNumberChanged = {} ,
        onVehicleChanged = {}
    )

}