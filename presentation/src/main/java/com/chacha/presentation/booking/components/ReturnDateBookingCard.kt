package com.chacha.presentation.booking.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingState
import com.chacha.presentation.common.theme.Border

@Composable
fun ReturnDateBookingCard(
    oneWayBookingState: OneWayBookingState,
    onDepartureDateChanged: (String) -> Unit,
    onReturnDateChanged: (String) -> Unit,
    onPassengerNumberChanged: (Int) -> Unit,
    onVehicleChanged: (String) -> Unit
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
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            // Add constraints for the first row
            val (departureDateField, returnDateField, divider) = createRefs()

            // Add constraints for the second row
            val (passengerField, vehicleField) = createRefs()

            // Add the first row of text fields

            DateTimeSelection(
                title = "Departure",
                hint  = oneWayBookingState.departureDate.ifEmpty {
                    "Select Date"
                },
                modifier =  Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {})
                    .constrainAs(departureDateField) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }

            )
/*            AppTextField(
                value = oneWayBookingState.departureDate,
                onValueChange = { departureDate ->
                    onDepartureDateChanged(departureDate)
                },
                title = "Departure",
                hint = "Select",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {})
                    .constrainAs(departureDateField) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )*/


            DateTimeSelection(
                title = "Return",
                hint  = if(oneWayBookingState.returnDate.isNotEmpty()){
                    oneWayBookingState.returnDate
                } else {
                    "Select"
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .clickable(onClick = {})
                    .constrainAs(returnDateField) {
                        top.linkTo(departureDateField.top)
                        start.linkTo(departureDateField.end)
                        bottom.linkTo(departureDateField.bottom)
                        end.linkTo(parent.end)
                    }

            )


            /*AppTextField(
                value = oneWayBookingState.departureDate,
                onValueChange = { departureDate ->
                    onDepartureDateChanged(departureDate)
                },
                title = "Returns",
                hint = "Select",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .clickable(onClick = {})
                    .constrainAs(returnDateField) {
                        top.linkTo(departureDateField.top)
                        start.linkTo(departureDateField.end)
                        bottom.linkTo(departureDateField.bottom)
                        end.linkTo(parent.end)
                    }
            )*/

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top= 10.dp, bottom = 10.dp)
                    .constrainAs(divider) {
                        top.linkTo(departureDateField.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground
            )

            // Add the second row of text fields
            AppTextField(
                value = oneWayBookingState.numberOfPassengers.toString(),
                title = "Passenger",
                hint = "Select Passenger",
                onValueChange = { passengers ->
                    onPassengerNumberChanged(passengers.toInt())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {})
                    .constrainAs(passengerField) {
                        top.linkTo(divider.bottom)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )

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
                    .constrainAs(vehicleField) {
                        top.linkTo(divider.bottom)
                        start.linkTo(passengerField.end)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
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