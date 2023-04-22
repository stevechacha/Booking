package com.chacha.presentation.book.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DepartureItem(
    departure: Departure
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = departure.departureStation
        )
        Text(
            text = departure.pickUpStation
        )

    }

}

data class Departure(
    val departureStation: String,
    val pickUpStation: String,

)