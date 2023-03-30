package com.chacha.presentation.book.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chacha.presentation.common.theme.BookingTheme
import com.chacha.presentation.common.theme.Border
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DatePickerCard(
    departureTitle: String,
    returnsTitle: String?,
    departureDate: String,
    returnDate: String?,
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
            .height(300.dp),
        border = BorderStroke(1.dp, Border),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(3.dp),

    ){
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
                    .padding(start=16.dp,end=16.dp,top= 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = departureTitle,
                        modifier = Modifier.clickable {
                            onDepartureDateClick()
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = departureDate,
                        modifier = Modifier
                            .clickable {
                                onDepartureDateClick()
                            }
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column {
                    Text(
                        text = returnsTitle ?: "",
                        modifier = Modifier.clickable {
                            onReturnDateClick()
                        }
                    )
                    Text(
                        text = returnDate ?: "",
                        modifier = Modifier.clickable {
                            onReturnDateClick()
                        }
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
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = vehicleType,
                    modifier = Modifier.clickable(onClick = onVehicleTypeClick)
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