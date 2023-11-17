package com.chacha.presentation.booking.booking_destination_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.booking_destination_list.component.DestinationItemComponent
import com.chacha.presentation.common.components.AppTopBar


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingDestinationList(
    onDepartureDestinationClick: () -> Unit,
    navController: NavController,
    bookingUiViewModel: BookingUiViewModel = viewModel(),
    onClose:()->Unit = {}
) {
    val bookingUiState by bookingUiViewModel.uiState.collectAsState()
    val availableDestination = bookingUiState.availableDeparture

    val selectedDepartureTowns = remember { mutableSetOf<String>() }

    val destinationFlights = availableDestination.filterNot {
        it.departureCity.city in selectedDepartureTowns
    }
    LaunchedEffect(Unit ){
        bookingUiViewModel.uiState.value.availableDeparture
        availableDestination.size

    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Search Destination",
                initialValue = bookingUiState.searchDestination,
                onSearchParamChange = { searchParams ->
                    bookingUiViewModel.handleUiEvent(
                        BookingUiEvent.SearchDestination(searchParams)
                    )
                },
                showSearchBar = true
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
        ) {
            items(destinationFlights.size) { index ->
                val flight = destinationFlights[index]
                DestinationItemComponent(
                    place = flight,
                    onSelectPlaceClick = { selectedFlight ->
                        bookingUiViewModel.handleUiEvent(
                            BookingUiEvent.DestinationPlaceSelected(place = selectedFlight.destinationCity.city)
                        )
                        onClose()
                    },
                )

                if (index < destinationFlights.size - 1) {
                    Divider()
                }
            }
        }
    }
}



@Composable
fun NoMatchFound() {
    Text(text = "Hello")
}

