package com.chacha.presentation.booking.tabs.one_way

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chacha.presentation.R
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.booking_destination_list.Flight
import com.chacha.presentation.booking.booking_destination_list.flightDateTimeFormatter
import com.chacha.presentation.booking.parsedDate
import com.chacha.presentation.booking.components.BookingCard
import com.chacha.presentation.booking.components.DateBookingCard
import com.chacha.presentation.booking.booking_bottom_sheet.BookingTypeBottomSheet
import com.chacha.presentation.booking.booking_bottom_sheet.BookingTypeBottomSheetLayout
import com.chacha.presentation.common.components.ContinueButton
import com.chacha.presentation.modal_sheet.WeBookingModalSheet
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(
    ExperimentalMaterialApi::class, ExperimentalPagerApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun OneWayBookingScreen(
    navController: NavController,
    bookingUiViewModel: BookingUiViewModel = viewModel(),
) {

    val context = LocalContext.current
    val  bookingUiState by bookingUiViewModel.uiState.collectAsState()
    val departure = bookingUiState.departurePlace
    val destination = bookingUiState.destinationPlace
    val departureDate = bookingUiState.departureDate


    var skipPartiallyExpanded by remember { mutableStateOf(true) }
    val sheetStateFullyExpanded = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val sheetStatePartiallyExpanded = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    val state = rememberDateRangePickerState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var currentBottomSheet: BookingTypeBottomSheet? by remember { mutableStateOf(null) }

    var showSearchResults by remember { mutableStateOf(true) }
    var searchResults by remember { mutableStateOf<List<Flight>>(emptyList()) }



    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val height = maxHeight
        val width = maxWidth
        val heightInDp = height.value.dp
        val widthInDp = width.value.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {

            BookingCard(
                bookingUiState = bookingUiState,
                onFromClick = {
                    isSheetOpen = true
                    currentBottomSheet = BookingTypeBottomSheet.DEPARTURE
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.DeparturePlaceSelected(it))

                },
                onToClick = {
                    isSheetOpen = true
                    currentBottomSheet = BookingTypeBottomSheet.DESTINATION
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.DestinationPlaceSelected(it))

                },
            )

            DateBookingCard(
                bookingUiState = bookingUiState,
                onDateChanged = {
                    isSheetOpen = true
                    currentBottomSheet = BookingTypeBottomSheet.ONE_BOOKING_DATE
                    state.selectedStartDateMillis?.let {
                        BookingUiEvent.DepartureDateSelected(
                            date = it.parsedDate()
                        )
                    }?.let { bookingUiViewModel.handleUiEvent(it) }
                },
                onTimeChanged = {},
                onFareChanged = {},
                onSeatChanged = {},
            )

            /* PassengerCardItem(
                 bookingUiState = bookingUiState ,
                 onPassengerNumberChanged = {
                     isSheetOpen = true
                     currentBottomSheet = BookingTypeBottomSheet.PASSENGERS
                 }
             )

             VehicleCardItem(
                 bookingUiState = bookingUiState,
                 onVehicleChanged = {
                     isSheetOpen = true
                     currentBottomSheet = BookingTypeBottomSheet.VEHICLE_TYPE
                 },
             )*/

            ContinueButton(
                text = stringResource(id = R.string.continuee),
                onClick = {

                }
            )

            ContinueButton(
                onClick = {
                    // Trigger the flight search when the user clicks the "Search" button.
                    searchResults = bookingUiViewModel.searchFlights(departure, destination, departureDate)

                    // Show the search results.
                    showSearchResults = true
                    isSheetOpen = true
                    currentBottomSheet = BookingTypeBottomSheet.PASSENGERS
                },
                text = stringResource(id = R.string.continuee),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                enabled = bookingUiState.isFormValid
            )


            /*if (showSearchResults) {
                // Show the search results content.
                SearchResultsContent(searchResults = searchResults)
            }*/
        }

    }

    if (isSheetOpen) {
        WeBookingModalSheet(
            onDismissRequest = {
                isSheetOpen = false
            },
            sheetState = when (currentBottomSheet) {
                BookingTypeBottomSheet.DEPARTURE -> sheetStateFullyExpanded
                BookingTypeBottomSheet.DESTINATION -> sheetStateFullyExpanded
                BookingTypeBottomSheet.ONE_BOOKING_DATE -> sheetStateFullyExpanded
                BookingTypeBottomSheet.RETURN_DATE -> sheetStateFullyExpanded
                BookingTypeBottomSheet.PASSENGERS -> sheetStatePartiallyExpanded
                BookingTypeBottomSheet.VEHICLE_TYPE -> sheetStatePartiallyExpanded
                null -> sheetStateFullyExpanded
            }
        ) {
            currentBottomSheet?.let {
                BookingTypeBottomSheetLayout(
                    bookingTypeBottomSheet = it,
                    closeSheet = { isSheetOpen = false },
                    onItemSelected = { isSheetOpen = false },
                    navController = navController,
                )
            }
        }
    }

}

@Composable
fun SearchResultsContent(
    searchResults: List<Flight>,
    onClick: (Flight) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(searchResults) { flight ->
            FlightItem(
                flight = flight,
                onClick = {
                    onClick(flight)
                }
            )
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun FlightItem(
    flight: Flight,
    onClick:(Flight)->Unit
) {
    Card(
        modifier = Modifier
            .clickable(){
                onClick(flight)
            }
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Departure: ${flight.departure.city}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "Destination: ${flight.destination.city}",
                fontSize = 16.sp
            )
            Text(
                text = "Time: ${flight.time.format(flightDateTimeFormatter)}",
                fontSize = 14.sp,
                color = Color.Gray
            )
            // You can add more flight details here as needed
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview
fun OneWayScreenPreview() {
    OneWayBookingScreen(
        navController = rememberNavController(),
        bookingUiViewModel = BookingUiViewModel(),
    )
}

