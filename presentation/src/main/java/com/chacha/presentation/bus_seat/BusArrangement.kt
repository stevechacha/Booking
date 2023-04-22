package com.chacha.presentation.bus_seat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun BusSeatArrangement(bookedSeats: List<Int>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(rows) { rowIndex, seats ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                seats.forEach { seatNumber ->
                    val isBooked = seatNumber in bookedSeats
                    Seat(seatNumber = seatNumber, isBooked = isBooked)
                }
            }
        }
    }
}

@Composable
fun Seat(seatNumber: Int, isBooked: Boolean) {
    Box(
        modifier = Modifier
            .size(35.dp, 35.dp)
            .background(if (isBooked) Color.Red else Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$seatNumber", color = Color.White)
    }
}

val rows = listOf(
    listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14),
    listOf(15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28),
    listOf(29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42),
    listOf(43, 44, 45, 46, 47, 48, 49, 50)
)

@Composable
@Preview
fun PreviewBusSeat() {
    BusSeatArrangement(
        bookedSeats = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    )
}

