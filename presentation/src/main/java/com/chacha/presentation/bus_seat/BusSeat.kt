package com.chacha.presentation.bus_seat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*@Composable
fun BusSeat() {
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
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                        .size(50.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A${rowIndex * 2 + 1}",
                        color = Color.White
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                        .size(50.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A${rowIndex * 2 + 2}",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(55.dp))

                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .size(50.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "B${rowIndex * 2 + 1}",
                        color = Color.White
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                        .size(50.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "B${rowIndex * 2 + 2}",
                        color = Color.White
                    )
                }
            }
        }
    }
}*/


@Composable
fun BusSeat() {
    val seatStatus = remember {
        // Initialize seat status as unbooked for all seats
        List(12) { List(4) { false } }
    }

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
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                        .size(50.dp)
                        .background(if (seatStatus[rowIndex][0]) Color.Red else Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A${rowIndex * 2 + 1}",
                        color = if (seatStatus[rowIndex][0]) Color.White else Color.Black
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                        .size(50.dp)
                        .background(if (seatStatus[rowIndex][1]) Color.Red else Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A${rowIndex * 2 + 2}",
                        color = if (seatStatus[rowIndex][1]) Color.White else Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(55.dp))

                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .size(50.dp)
                        .background(if (seatStatus[rowIndex][2]) Color.Red else Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "B${rowIndex * 2 + 1}",
                        color = if (seatStatus[rowIndex][2]) Color.White else Color.Black
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                        .size(50.dp)
                        .background(if (seatStatus[rowIndex][3]) Color.Red else Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "B${rowIndex * 2 + 2}",
                        color = if (seatStatus[rowIndex][3]) Color.White else Color.Black
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun BusSeatPreview() {
    BusSeat()
}