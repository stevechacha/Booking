package com.chacha.presentation.booking.departure_result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DepartureResultList(
    navController: NavController,
    destinationPlace: String,
    destinationCode: String,
    departureCode: String,
    departurePlace: String,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(text = departurePlace)
                Text(text = departureCode)
            }
            Column {
                Text(text = destinationPlace)
                Text(text = destinationCode)
            }

        }

    }

}