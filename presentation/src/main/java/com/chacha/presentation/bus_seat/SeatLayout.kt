package com.chacha.presentation.bus_seat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Composable
fun SeatLayout(seatList: List<Seat>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(12) { rowIndex ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(2) { columnIndex ->
                    val seatIndex = rowIndex * 4 + columnIndex
                    val seat = seatList.getOrNull(seatIndex)
                    if (seat != null) {
                        Box(
                            modifier = Modifier
                                .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                                .size(50.dp)
                                .background(if (seat.isBooked) Color.Gray else Color.Green),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = seat.id,
                                color = if (seat.isBooked) Color.White else Color.Black
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.width(50.dp))
                    }
                }
                Spacer(modifier = Modifier.width(25.dp))
                repeat(2) { columnIndex ->
                    val seatIndex = rowIndex * 4 + columnIndex + 2
                    val seat = seatList.getOrNull(seatIndex)
                    if (seat != null) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                                .background(if (seat.isBooked) Color.Gray else Color.Green),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = seat.id,
                                color = if (seat.isBooked) Color.White else Color.Black
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.width(50.dp))
                    }
                }
            }
        }
    }
}




data class Seats(val id: String, var isBooked: Boolean = false)

val seatLists = listOf(
    Seat("A1"), Seat("A2"), Seat("A3"), Seat("A4"), Seat("A5"), Seat("A6"), Seat("A7"), Seat("A8"), Seat("A9"), Seat("A10"),
    Seat("A11"), Seat("A12"), Seat("A13"), Seat("A14"), Seat("A15"), Seat("A16"), Seat("A17"), Seat("A18"), Seat("A19"), Seat("A20"),
    Seat("A21"), Seat("A22"), Seat("A23"), Seat("A24"), Seat("A25"), Seat("A26"), Seat("A27"), Seat("A28"), Seat("A29"), Seat("A30"),
    Seat("B1"), Seat("B2"), Seat("B3"), Seat("B4"), Seat("B5"), Seat("B6"), Seat("B7"), Seat("B8"), Seat("B9"), Seat("B10"),
    Seat("B11"), Seat("B12"), Seat("B13"), Seat("B14"), Seat("B15"), Seat("B16"), Seat("B17"), Seat("B18"), Seat("B19"), Seat("B20"),
    Seat("B21"), Seat("B22"), Seat("B23"), Seat("B24"), Seat("B25"), Seat("B26"), Seat("B27"), Seat("B28"), Seat("B29"), Seat("B30")
)


@Composable
fun Seat() {
    SeatLayout(seatList)

}



@Composable
@Preview
fun PreviewSeatLayout() {
    SeatLayout(seatList)
}


