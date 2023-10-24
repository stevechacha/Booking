package com.chacha.presentation.booking.booking_destination_list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chacha.presentation.booking.booking_destination_list.Flight

@Composable
fun DepartureItemComponent(
    place: Flight,
    onSelectPlaceClick:(Flight)->Unit
) {

    Row(
        modifier = Modifier
                .clickable { onSelectPlaceClick(place) }
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationCity,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = place.departure.city,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = place.departure.code,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }



}