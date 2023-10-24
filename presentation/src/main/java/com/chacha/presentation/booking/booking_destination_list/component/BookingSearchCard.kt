package com.chacha.presentation.booking.booking_destination_list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BookingSearchCard(
    departure: String? = null,
    departureHint: String = "Select Departure",
    destination: String? = null,
    onClick: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "departure")
            if (departure == null) {
                Text(
                    text = departureHint,
                    modifier = Modifier.clickable(interactionSource, null) {
                        onClick(departureHint)
                    },
                    color = Color.Red
                )

            } else {
                Text(
                    text = departure,
                    modifier = Modifier.clickable(interactionSource, null) {
                        onClick(departure)
                    },
                    color = Color.Black
                )
            }

            Text(
                text = departure ?: "Select departure",
                modifier = Modifier.clickable(interactionSource, null) {
                    if (departure != null) {
                        onClick(departure)
                    }
                },
                color = if (departure != null) Color.Black else Color.Red
            )
            Divider()
            Text(text = "From")
            Text(
                text = destination ?: "Select Destination",
                modifier = Modifier.clickable(interactionSource, null) {
                    if (destination != null) {
                        onClick(destination)
                    }
                }
            )

        }
    }

}