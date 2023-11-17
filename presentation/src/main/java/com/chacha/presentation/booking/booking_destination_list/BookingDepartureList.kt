package com.chacha.presentation.booking.booking_destination_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.booking_destination_list.component.DepartureItemComponent
import com.chacha.presentation.common.components.AppTopBar



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingDepartureList(
    bookingUiViewModel: BookingUiViewModel = viewModel(),
    onDepartureDestinationClick: () -> Unit,
    navController: NavController,
    onClose:()->Unit = {}
) {
    val bookingUiState by bookingUiViewModel.uiState.collectAsState()
    val availableDepartures = bookingUiState.availableDeparture

    val selectedDepartureTowns = remember { mutableSetOf<String>() }

    val departureFlights = availableDepartures.filterNot {
        it.destinationCity.city in selectedDepartureTowns
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Search Departure",
                initialValue = bookingUiState.searchDepartureDestination,
                onSearchParamChange = { searchParams ->
                    bookingUiViewModel.handleUiEvent(
                        BookingUiEvent.SearchDepartureDestination(searchParams)
                    )
                },
                showSearchBar = true
            )
        }
    ) { paddingValues ->

        if (bookingUiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            if (bookingUiState.availableDeparture.isEmpty()) {
                NoMatchFound()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues = paddingValues)
                ) {
                    items(availableDepartures.size) { index ->
                        val flight = availableDepartures[index]
                        DepartureItemComponent(
                            place = flight,
                            onSelectPlaceClick = { selectedFlight ->
                                bookingUiViewModel.handleUiEvent(
                                    BookingUiEvent.DeparturePlaceSelected(place = selectedFlight.departureCity.city)
                                )
                                bookingUiViewModel.handleUiEvent(
                                    BookingUiEvent.DeparturePickupStationSelected(station = selectedFlight.departureCity.code)
                                )
                                bookingUiViewModel.handleUiEvent(
                                    BookingUiEvent.DeparturePickupStationSelected(station = selectedFlight.destinationCity.city)
                                )
                                // Add the selected departure town to the set
                                selectedDepartureTowns.add(selectedFlight.destinationCity.city)
                                onClose()
                            },
                        )

                        if (index < departureFlights.size - 1) {
                            Divider()
                        }
                    }
                }
            }

        }

    }
}



