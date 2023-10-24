package com.chacha.presentation.booking.tabs.returns_booking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chacha.presentation.R
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.parsedDate
import com.chacha.presentation.booking.components.BookingCard
import com.chacha.presentation.booking.components.PassengerCardItem
import com.chacha.presentation.booking.components.ReturnDateBookingCard
import com.chacha.presentation.booking.components.VehicleCardItem
import com.chacha.presentation.booking.booking_bottom_sheet.BookingTypeBottomSheet
import com.chacha.presentation.booking.booking_bottom_sheet.BookingTypeBottomSheetLayout
import com.chacha.presentation.booking.booking_destination_list.BusBooking
import com.chacha.presentation.common.components.ContinueButton
import com.chacha.presentation.modal_sheet.WeBookingModalSheet
import com.google.accompanist.pager.ExperimentalPagerApi
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(
    ExperimentalPagerApi::class,
)
@Composable
fun ReturnBookingScreen(
    navController: NavController,
) {
    val bookingUiViewModel: BookingUiViewModel = viewModel()
    val bookingUiState by bookingUiViewModel.uiState.collectAsState()
    val skipPartiallyExpanded by remember { mutableStateOf(true) }
    val sheetStateFullyExpanded = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val sheetStatePartiallyExpanded = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    val departure = bookingUiState.departurePlace
    val destination = bookingUiState.destinationPlace
    val departureDate = bookingUiState.departureDate

    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var currentBottomSheet: BookingTypeBottomSheet? by remember { mutableStateOf(null) }
    val dateTime = LocalDateTime.now()
    var showSearchResults by remember { mutableStateOf(true) }
    var searchResults by remember { mutableStateOf<List<BusBooking>>(emptyList()) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookingCard(
            bookingUiState = bookingUiState,
            onFromClick = {
                isSheetOpen = true
                currentBottomSheet = BookingTypeBottomSheet.DEPARTURE
            },
            onToClick = {
                isSheetOpen = true
                currentBottomSheet = BookingTypeBottomSheet.DESTINATION
            },
        )

        ReturnDateBookingCard(
            bookingUiState = bookingUiState,
            onReturnDateChanged = {
                isSheetOpen = true
                currentBottomSheet = BookingTypeBottomSheet.RETURN_DATE
            },
            onDepartureDateChanged = {
                isSheetOpen = true
                currentBottomSheet = BookingTypeBottomSheet.RETURN_DATE

            },
        )



        VehicleCardItem(
            bookingUiState = bookingUiState,
            onVehicleChanged = {
                isSheetOpen = true
                currentBottomSheet = BookingTypeBottomSheet.VEHICLE_TYPE
            },
        )

        ContinueButton(
            onClick = {
                // Trigger the flight search when the user clicks the "Search" button.
                searchResults = bookingUiViewModel.searchFlights(departure, destination, departureDate.toString())
                // Show the search results.
                showSearchResults = true
                isSheetOpen = true
                currentBottomSheet = BookingTypeBottomSheet.PASSENGERS
            },
            text = stringResource(id = R.string.continuee),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = bookingUiState.isFormValid
        )
    }



    if (isSheetOpen) {
        WeBookingModalSheet(
            onDismissRequest = {
                isSheetOpen = false
            },
            sheetState = when(currentBottomSheet){
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
                    onItemSelected = {
                        isSheetOpen = false
                    },
                    navController = navController,
                )
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview
fun ReturnBookingPreview() {
    ReturnBookingScreen(
        navController = rememberNavController(),
    )
}
