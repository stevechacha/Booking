package com.chacha.presentation.booking.booking_search_route

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chacha.presentation.booking.model.BusBooking
import com.chacha.presentation.booking.model.flightDateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun FlightItem(
    busBooking: BusBooking,
    onClick: (BusBooking) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick(busBooking) }
            .fillMaxWidth()
            .padding(16.dp),
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                ) {
                    Text(
                        text = "${busBooking.departureCity.city.uppercase()} ${busBooking.departureCity.code.uppercase()}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "${busBooking.destinationCity.city.uppercase()} ${busBooking.destinationCity.code.uppercase()}",
                        fontSize = 16.sp
                    )
                    Text(
                        text = busBooking.time.format(flightDateTimeFormatter),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }


                Text(
                    text = "Available seats: ${busBooking.availableSeats}",
                )


            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(text = "KES ${busBooking.fare}")
            }
        }


    }
}
