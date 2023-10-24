package com.chacha.presentation.booking.booking_bottom_sheet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chacha.presentation.booking.booking_destination_list.BookingDepartureList
import com.chacha.presentation.booking.booking_destination_list.BookingDestinationList
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.booking_destination_list.Flight
import com.chacha.presentation.booking.parsedDate
import com.chacha.presentation.booking.tabs.one_way.SearchResultsContent
import com.chacha.presentation.common.navigation.GraphDestinations
import com.chacha.presentation.compose.Example2Page
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingTypeBottomSheetLayout(
    bookingTypeBottomSheet: BookingTypeBottomSheet,
    navController: NavController,
    onItemSelected: () -> Unit,
    closeSheet: () -> Unit,
    bookingUiViewModel: BookingUiViewModel = viewModel(),
) {
    val dateTime = LocalDateTime.now()
    val state = rememberDateRangePickerState()

    val  bookingUiState by bookingUiViewModel.uiState.collectAsState()
    val departure = bookingUiState.departurePlace
    val destination = bookingUiState.destinationPlace
    val departureDate = bookingUiState.departureDate
    var searchResults by remember { mutableStateOf<List<Flight>>(emptyList()) }
    searchResults = bookingUiViewModel.searchFlights(departure, destination, departureDate)


    AnimatedContent(
        targetState = bookingTypeBottomSheet,
        label = "",
    ) { bookingTypeBottomSheets->
        when (bookingTypeBottomSheets) {

            BookingTypeBottomSheet.DEPARTURE -> BookingDepartureList(
                onDepartureDestinationClick = {
                    onItemSelected()
                    closeSheet ()
                },
                navController = navController,
                bookingUiViewModel = bookingUiViewModel,
                onClose = { closeSheet()}

            )

            BookingTypeBottomSheet.DESTINATION -> BookingDestinationList(
                onDepartureDestinationClick = {
                    onItemSelected()
                    closeSheet()
                },
                navController = navController,
                onClose = { closeSheet()}
            )

            BookingTypeBottomSheet.ONE_BOOKING_DATE -> SingleBookingDatePicker(
                onDateRangePickerState = {
                    state.selectedStartDateMillis?.let {
                        BookingUiEvent.DepartureDateSelected(
                            date = it.parsedDate()
                        )
                    }?.let { bookingUiViewModel.handleUiEvent(it) }
                },
                closeSheet = { closeSheet() }
            )
/*
            BookingTypeBottomSheet.RETURN_DATE -> BookingDateRangePicker(
                onDateRangePickerState = {
                    state.selectedStartDateMillis?.let {
                        state.selectedEndDateMillis?.let { it1 ->
                            BookingUiEvent.ReturnDateSelected(
                                departureDate = it.parsedDate(),
                                endDate = it1.parsedDate()
                            )
                        }
                    }?.let {
                        bookingUiViewModel.handleUiEvent(
                            it
                        )
                    }
                    onItemSelected()
                },
                closeSheet = { closeSheet() }

            )*/

            BookingTypeBottomSheet.RETURN_DATE -> Example2Page()

            BookingTypeBottomSheet.PASSENGERS -> SearchResultsContent(
                searchResults = searchResults,
                onClick = {
                    navController.navigate(GraphDestinations.SEARCH_RESULT)
                }
            )
            BookingTypeBottomSheet.VEHICLE_TYPE -> BookingVehicleTypeBottomSheet()
        }

    }



}


enum class BookingTypeBottomSheet {
    DEPARTURE,
    DESTINATION,
    ONE_BOOKING_DATE,
    RETURN_DATE,
    PASSENGERS,
    VEHICLE_TYPE
}