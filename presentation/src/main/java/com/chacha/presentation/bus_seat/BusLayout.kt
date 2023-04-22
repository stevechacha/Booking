package com.chacha.presentation.bus_seat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chacha.presentation.R

@Composable
fun BusSeatArrangement(seatList: List<Seat>) {
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
                repeat(4) { columnIndex ->
                    val seatIndex = rowIndex * 4 + columnIndex
                    val seat = seatList[seatIndex]
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(50.dp)
                            .background(if (seat.isBooked) Color.Green else Color.Gray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = seat.id,
                            color = if (seat.isBooked) Color.White else Color.Black
                        )
                    }
                }
            }
        }
    }
}

data class Seat(val id: String, var isBooked: Boolean = false)

val seatList = listOf(
    Seat("A1"), Seat("A2"), Seat("B1"), Seat("B2"), Seat("A3"), Seat("A4"), Seat("B3"), Seat("B4"), Seat("A5"), Seat("A6"),
    Seat("B5"), Seat("B6"), Seat("A7"), Seat("A8"), Seat("B7"), Seat("B8"), Seat("A9"), Seat("A10"), Seat("B9"), Seat("B10"),
    Seat("A11"), Seat("A12"), Seat("B11"), Seat("B12"), Seat("A13"), Seat("A14"), Seat("B13"), Seat("B14"), Seat("A15"), Seat("A16"),
    Seat("B15"), Seat("B16"), Seat("A17"), Seat("A18"), Seat("B17"), Seat("B18"), Seat("A19"), Seat("A20"), Seat("B19"), Seat("B20"),
    Seat("A21"), Seat("A22"), Seat("B21"), Seat("B22"), Seat("A23"), Seat("A24"), Seat("B23"), Seat("B24"),
)

@Composable
@Preview
fun SeatImageWithText() {
    ConstraintLayout(
        modifier = Modifier.size(50.dp)
    ) {
        // Load the image
        val image = painterResource(id = R.drawable.chair_icon)

        // Add the image using an Image composable
        val imageRef = createRef()
        Image(
            painter = image,
            contentDescription = "Seat Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // Add the text on top of the image using a Text composable
        val textRef = createRef()
        Text(
            text = "A1",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.constrainAs(textRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview
fun ChooseVehicle() {
    Column(modifier = Modifier.fillMaxWidth()){
        Text(text = "Choose Vehicle", modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.chair_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        RoundedCornerShape(8.dp)
                    )
            )

            Text(text = "Car", modifier = Modifier.padding(8.dp))

            RadioButton(
                selected = true,
                onClick = { /*TODO*/ }
            )

        }
        Divider()

        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.airport_shuttle),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        RoundedCornerShape(8.dp)
                    )
            )

            Text(text = "Bus", modifier = Modifier.padding(8.dp))

            RadioButton(
                selected = true ,
                onClick = { /*TODO*/ }
            )

        }

    }

}

@Composable
@Preview
fun ChooseVehicles() {
    val vehicleType = remember { mutableStateOf("Car") }

    Column(modifier = Modifier.fillMaxWidth()){
        Text(text = "Choose Vehicle", modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.directions_bus),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        RoundedCornerShape(8.dp)
                    )
            )

            Text(
                text = "Car",
                modifier = Modifier.padding(start=8.dp),
                textAlign = TextAlign.Center

            )
            Spacer(modifier = Modifier.weight(1f))

            RadioButton(
                selected = vehicleType.value == "Car",
                onClick = { vehicleType.value = "Car" }
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(id = R.drawable.airport_shuttle),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        RoundedCornerShape(8.dp)
                    )
            )

            Text(
                text = "Bus",
                modifier = Modifier.padding(start=8.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            RadioButton(
                selected = vehicleType.value == "Bus",
                onClick = { vehicleType.value = "Bus" }
            )

        }

    }

    Text(
        text = "Choose Vehicle Type: ${vehicleType.value}",
        modifier = Modifier.padding(8.dp)
    )
}



@Preview
@Composable
fun BusSeatLayoutPreview() {
    BusSeatArrangement(seatList)
}
