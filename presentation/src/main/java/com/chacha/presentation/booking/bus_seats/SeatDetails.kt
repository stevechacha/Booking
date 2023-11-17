package com.chacha.presentation.booking.bus_seats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiState
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.model.BusBooking
import com.chacha.presentation.booking.model.Gender
import com.chacha.presentation.booking.model.SeatDetailsEntry
import com.chacha.presentation.common.components.AppToolbar
import com.chacha.presentation.common.components.BookingTextField
import com.chacha.presentation.common.components.ContinueButton
import com.chacha.presentation.common.components.RideCard


@Composable
fun SeatDetailsScreen(
    navController: NavController,
    numSeats: Int,
    seatLabels: String,
    bookingUiState : BookingUiState
) {
    val bookingUiViewModel: BookingUiViewModel = viewModel()
    val bookingUiState by bookingUiViewModel.uiState.collectAsState()

//    val seatDetails = remember { mutableStateListOf<SeatDetails>() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destinationPlace = navBackStackEntry?.arguments?.getString("destination") ?: ""
    val destinationCode = navBackStackEntry?.arguments?.getString("destinationCode") ?: ""
    val departurePlace = navBackStackEntry?.arguments?.getString("departurePlace") ?: ""
    val departureCode = navBackStackEntry?.arguments?.getString("departureCode") ?: ""

    val seatDetails = remember { mutableStateListOf<BusBooking>() }


    Scaffold(
        topBar = {
            AppToolbar(
                showBackArrow = true,
                title = "${departurePlace.uppercase()} To ${destinationPlace.uppercase()}"
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            repeat(numSeats) { seatIndex ->
                SeatDetailsForm(
                    seatLabel = seatLabels,
                    seatIndex = seatIndex,
                    seatDetails = seatDetails,
                    onSeatDetailsEntered = { details ->
                        val departureCity = details.departureCity.city
                        val departureCode = details.departureCity.code
                        val destinationCity = details.destinationCity.city
                        val destinationCode = details.destinationCity.code
                        seatDetails[seatIndex] = details
                        navController.navigate("confirm/${departureCity}/${departureCode}/${departureCode}")


                    }
                )
            }

            ContinueButton(

                onClick = {
                    navController.navigate("confirm/${seatLabels}")
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.OnClick)

                },
                text = "Confirm",
                enabled = true
            )
        }
    }
}

@Composable
fun SeatDetailsForm(
    seatLabel: String,
    seatIndex: Int,
    seatDetails: List<BusBooking>,
    onSeatDetailsEntered: (BusBooking) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var idNumber by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf(Gender.MALE) }
    var contact by remember { mutableStateOf("") }

    val bookingUiViewModel: BookingUiViewModel = viewModel()
    val bookingUiState by bookingUiViewModel.uiState.collectAsState()
    val details = seatDetails.getOrElse(seatIndex) { bookingUiState.bookingDetails }


    // Composable for the form
    RideCard {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // TextFields for user details
            Text(text = seatLabel)
            BookingTextField(
                value = name,
                onValueChange = { name = it },
                hint = "Name",
                title = "Name",
                keyboardType = KeyboardType.Text,
            )
            BookingTextField(
                value = idNumber,
                onValueChange = { idNumber = it },
                hint = "ID",
                title = "ID",
                keyboardType = KeyboardType.Number,
            )

            BookingTextField(
                value = contact,
                onValueChange = { contact = it },
                hint = "Contact",
                title = "Contact",
                keyboardType = KeyboardType.Phone,
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Gender")
                    GenderRadioButton(
                        onSelected = { genderType ->
                            gender = genderType
                        },
                        gender = Gender.MALE,
                        isSelected = gender.type == Gender.MALE.type

                    )
                    GenderRadioButton(
                        onSelected = { genderType ->
                            gender = genderType
                        },
                        gender = Gender.FEMALE,
                        isSelected  = gender.type == Gender.FEMALE.type
                    )
                }
            }


            Button(onClick = {
                if (details != null) {
                    onSeatDetailsEntered(details)
                }
            }) {
                Text("Submit")
            }

        }
    }

}

@Composable
fun GenderRadioButton(
    isSelected: Boolean = false,
    onSelected: (Gender) -> Unit,
    gender: Gender
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelected(gender) }
        )
        Text(text = gender.type)
    }

}


