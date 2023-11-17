package com.chacha.presentation.booking.bus_seats

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chacha.presentation.booking.BookingUiState
import com.chacha.presentation.common.components.AppToolbar
import com.chacha.presentation.common.components.ContinueButton


@Composable
fun BusSeat(
    navController: NavController,
    bookingUiState: BookingUiState
) {
    val selectedSeats = remember { mutableStateListOf<Pair<Int, Int>>() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destinationPlace = navBackStackEntry?.arguments?.getString("destination") ?: ""
    val destinationCode = navBackStackEntry?.arguments?.getString("destinationCode") ?: ""
    val departurePlace = navBackStackEntry?.arguments?.getString("departurePlace") ?: ""
    val departureCode = navBackStackEntry?.arguments?.getString("departureCode") ?: ""

    Scaffold(
        topBar = {
            AppToolbar(
                showBackArrow = true,
                title = "${departurePlace.uppercase()}   To   ${destinationPlace.uppercase()}"
            )
        }
    ) { paddingValues ->
        val seatStatus = remember {
            List(12) { MutableList(5) { SeatStatus.AVAILABLE } }
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(12) { rowIndex ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    repeat(5) { columnIndex ->
                        SeatColumn(
                            seatStatus = seatStatus,
                            rowIndex = rowIndex,
                            columnIndex = columnIndex,
                            selectedSeats = selectedSeats
                        )
                    }
                }
            }

            ContinueButton(
                onClick = {
                    navController.navigate("seatDetails/${selectedSeats.size}/${selectedSeats}/${destinationPlace}/${destinationCode}/${departurePlace}/${departureCode}")
                },
                text = "Next",
                enabled = true
            )

            selectedSeats.forEachIndexed { index, seat ->
                Text(
                    "seatLabel${index}, ${
                        getSeatLabel(
                            seat.first,
                            seat.second + 1
                        )
                    }"
                )
            }


        }
    }
}

@Composable
fun SeatColumn(
    seatStatus: List<MutableList<SeatStatus>>,
    rowIndex: Int,
    columnIndex: Int,
    selectedSeats: MutableList<Pair<Int, Int>>
) {
    val isCenterColumn = columnIndex == 2 // Check if it's the center column
    val isCenterSpace =
        isCenterColumn && rowIndex < 11 // Check if it's a space in the center column
    val seatModifier = Modifier
        .clickable {
            if (!isCenterSpace) {
                // Handle seat click, update seat status
                seatStatus[rowIndex][columnIndex] = when (seatStatus[rowIndex][columnIndex]) {
                    SeatStatus.BOOKED -> SeatStatus.BOOKED
                    SeatStatus.AVAILABLE -> SeatStatus.SELECTED
                    SeatStatus.SELECTED -> SeatStatus.AVAILABLE
                }

                // Track selected seats
                val seatIndex = rowIndex to columnIndex
                if (seatIndex in selectedSeats) {
                    selectedSeats.remove(seatIndex)
                } else {
                    selectedSeats.add(seatIndex)
                }
            }
        }
        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
        .size(40.dp)
        .background(getSeatColor(seatStatus[rowIndex][columnIndex], isCenterSpace))

    Box(
        modifier = if ((rowIndex to columnIndex) in selectedSeats) {
            seatModifier.border(2.dp, Color.Yellow) // Add border for selected seats
        } else {
            seatModifier
        },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getSeatLabel(columnIndex, rowIndex),
            color = getSeatTextColor(seatStatus[rowIndex][columnIndex], isCenterSpace)
        )
    }
}


fun getSeatLabel(columnIndex: Int, rowIndex: Int): String {
    return when {
        columnIndex < 2 -> "A${rowIndex * 2 + columnIndex + 1}" // First two columns
        columnIndex == 2 && rowIndex == 11 -> "25" // Seat 25 between A24 and B23
        columnIndex == 2 && rowIndex < 11 -> " " // Space in the center
        columnIndex >= 3 -> "B${rowIndex * 2 + columnIndex - 2}" // Last two columns
        else -> ""
    }
}

// Updated getSeatColor function to handle center space color
@Composable
fun getSeatColor(status: SeatStatus, isCenterSpace: Boolean): Color {
    return when {
        status == SeatStatus.BOOKED -> Color.Gray
        isCenterSpace -> Color.Transparent
        status == SeatStatus.AVAILABLE -> Color.White // White color for center space
        status == SeatStatus.SELECTED -> Color.Red // You can use a different color for selected seats
        else -> Color.Gray
    }
}

// Updated getSeatTextColor function to handle center space color
@Composable
fun getSeatTextColor(status: SeatStatus, isCenterSpace: Boolean): Color {
    return when {
        status == SeatStatus.BOOKED -> Color.White
        status == SeatStatus.AVAILABLE && isCenterSpace -> Color.White // White color for center space
        status == SeatStatus.SELECTED -> Color.Black
        else -> Color.Black
    }
}

enum class SeatStatus {
    BOOKED,
    AVAILABLE,
    SELECTED
}


@Preview(showBackground = true)
@Composable
fun BusSeatPreview() {
    BusSeat(
        navController = rememberNavController(),
        bookingUiState = BookingUiState()
    )
}