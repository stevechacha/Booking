package com.chacha.presentation.booking.departure_result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chacha.presentation.booking.BookingUiState

@Composable
fun DepartureResultList(
    navController: NavController,
    bookingUiState: BookingUiState
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destinationPlace = navBackStackEntry?.arguments?.getString("destination") ?: ""
    val destinationCode = navBackStackEntry?.arguments?.getString("destinationCode") ?: ""
    val departurePlace = navBackStackEntry?.arguments?.getString("departurePlace") ?: ""
    val departureCode = navBackStackEntry?.arguments?.getString("departureCode") ?: ""

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