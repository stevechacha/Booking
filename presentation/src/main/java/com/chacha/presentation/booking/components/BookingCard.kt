package com.chacha.presentation.booking.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chacha.presentation.R
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingState
import com.chacha.presentation.common.navigation.GraphDestinations.DEPARTURE_DESTINATION
import com.chacha.presentation.common.theme.Border

@Composable

fun BookingCard(
    oneWayBookingState: OneWayBookingState,
    onFromClick: (String) -> Unit,
    onToClick: (String) -> Unit,
    navController: NavController
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AppTextField(
                value = oneWayBookingState.departureDestination,
                onValueChange = { departure ->
                    onFromClick(departure)
                },
                title = "From",
                hint = "Select Departure",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { navController.navigate(DEPARTURE_DESTINATION) })
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth(0.91f),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_interchange),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )

            }

            AppTextField(
                value = oneWayBookingState.arrivalDestination,
                title = "To",
                hint = "Select Destination",
                onValueChange = { destination ->
                    onToClick(destination)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {})
            )
        }

    }

}

@Preview
@Composable
fun BookingCardPreview() {
    BookingCard(
        oneWayBookingState = OneWayBookingState(),
        onFromClick = {},
        onToClick = {},
        navController = NavController(LocalContext.current)
    )
}