package com.chacha.presentation.book.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chacha.presentation.common.theme.BookingTheme
import com.chacha.presentation.common.theme.Border

@Composable
fun DatePickerCard(
    departureTitle: String,
    returnsTitle: String,
    departureDate: String,
    returnDate: String,
    numberOfPassenger: String,
    vehicleType: String,
    onDepartureDateClick: () -> Unit,
    onReturnDateClick: () -> Unit,
    onPassengerClick: () -> Unit,
    onVehicleTypeClick: () -> Unit,

    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        border = BorderStroke(1.dp, Border),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(3.dp),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = departureTitle,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = departureDate,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable(onClick = onDepartureDateClick),
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis

                    )

                }
                Spacer(modifier = Modifier.weight(1f))
                Column {
                    Text(
                        text = returnsTitle,
                        modifier = Modifier.clickable {
                            onReturnDateClick()
                        },
                        overflow = TextOverflow.Ellipsis

                    )
                    Text(
                        text = returnDate,
                        modifier = Modifier.clickable {
                            onReturnDateClick()
                        },
                        overflow = TextOverflow.Ellipsis

                    )
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = numberOfPassenger,
                    modifier = Modifier.clickable {
                        onPassengerClick()
                    },
                    overflow = TextOverflow.Ellipsis

                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = vehicleType,
                    modifier = Modifier.clickable(onClick = onVehicleTypeClick),
                    overflow = TextOverflow.Ellipsis

                )

            }
        }

    }


}

@Composable
@Preview
fun DatePickerPriview() {
    BookingTheme {
        DatePickerCard(
            departureTitle = "Departure",
            returnsTitle = "Returns",
            departureDate = "12/12/2021",
            returnDate = "12/12/2021",
            numberOfPassenger = "1 Passenger",
            vehicleType = "Economy",
            onDepartureDateClick = {},
            onReturnDateClick = {},
            onPassengerClick = {},
            onVehicleTypeClick = {}
        )
    }
}