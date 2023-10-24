package com.chacha.presentation.trips.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.chacha.presentation.R

@Composable
fun RecentTripItem() {
    Box( ){
        Column {
            Icon(
                painter = painterResource(id = R.drawable.airline_seat_recline ),
                contentDescription = null
            )
            Text(text = "Trip Name")

        }
    }
}