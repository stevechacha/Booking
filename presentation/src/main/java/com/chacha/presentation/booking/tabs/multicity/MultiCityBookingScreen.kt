package com.chacha.presentation.booking.tabs.multicity

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chacha.presentation.R
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.components.MultiCityBookingCard
import com.chacha.presentation.booking.components.PassengerCardItem
import com.chacha.presentation.booking.components.VehicleCardItem
import com.chacha.presentation.booking.booking_bottom_sheet.BookingTypeBottomSheet
import com.chacha.presentation.booking.booking_bottom_sheet.BookingTypeBottomSheetLayout
import com.chacha.presentation.booking.booking_calender.dateFormatterr
import com.chacha.presentation.booking.booking_component.FromUserInput
import com.chacha.presentation.booking.booking_component.ToUserInput
import com.chacha.presentation.booking.booking_destination_list.BusBooking
import com.chacha.presentation.common.components.ContinueButton
import com.chacha.presentation.modal_sheet.WeBookingModalSheet
import com.google.accompanist.pager.ExperimentalPagerApi
import java.time.LocalDateTime

@OptIn( ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MultiCityBookingScreen(
    navController: NavController
) {
    val bookingUiViewModel: BookingUiViewModel = viewModel()
    val bookingUiState by bookingUiViewModel.uiState.collectAsState()
    val departure = bookingUiState.departurePlace
    val destination = bookingUiState.destinationPlace
    val departureDate = bookingUiState.departureDate
    val context = LocalContext.current

    val skipPartiallyExpanded by remember { mutableStateOf(true) }
    val sheetStateFullyExpanded = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val sheetStatePartiallyExpanded = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    val interactionSource = remember { MutableInteractionSource() }

    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var currentBottomSheet: BookingTypeBottomSheet? by remember { mutableStateOf(null) }
//    var searchResults by remember { mutableStateOf<List<BusBooking>>(emptyList()) }

    var searchResults = bookingUiState.searchResults
    var showSearchResults by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        MultiCityBookingCard(
            fromContent = {
                    FromUserInput(
                        destinationSelected = bookingUiState.departurePlace,
                        onDestinationSelectionClicked = {
                            isSheetOpen = true
                            currentBottomSheet = BookingTypeBottomSheet.DEPARTURE
                            bookingUiViewModel.handleUiEvent(BookingUiEvent.DeparturePlaceSelected(it))
                        }
                    )

            },
            toContent = {
                ToUserInput(
                        destinationSelected = bookingUiState.destinationPlace,
                        onDestinationSelectionClicked = {
                            isSheetOpen = true
                            currentBottomSheet = BookingTypeBottomSheet.DESTINATION
                            bookingUiViewModel.handleUiEvent(BookingUiEvent.DestinationPlaceSelected(it))
                        }
                    )

            },
            dateContent = {
                Text(
                    text  = dateFormatterr.format(bookingUiState.departureDate).toString() ,
                    modifier = Modifier.clickable(interactionSource,null){
                        isSheetOpen = true
                        currentBottomSheet = BookingTypeBottomSheet.ONE_BOOKING_DATE

                    },
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp
                )

            }
        )

        MultiCityBookingCard(
            flightNo = 2,
            fromContent = {
                    FromUserInput(
                        destinationSelected = bookingUiState.departurePlace,
                        onDestinationSelectionClicked = {
                            isSheetOpen = true
                            currentBottomSheet = BookingTypeBottomSheet.DEPARTURE
                            bookingUiViewModel.handleUiEvent(BookingUiEvent.DeparturePlaceSelected(it))
                        }
                    )


            },
            toContent = {
                    ToUserInput(
                        destinationSelected = bookingUiState.destinationPlace,
                        onDestinationSelectionClicked = {
                            isSheetOpen = true
                            currentBottomSheet = BookingTypeBottomSheet.DESTINATION
                            bookingUiViewModel.handleUiEvent(BookingUiEvent.DestinationPlaceSelected(it))
                        }
                    )
            },
            dateContent = {
                Text(
                    text  = dateFormatterr.format(bookingUiState.returnDate).toString() ,
                    modifier = Modifier.clickable(interactionSource,null){
                        isSheetOpen = true
                        currentBottomSheet = BookingTypeBottomSheet.ONE_BOOKING_DATE
                    },
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp
                )

            }
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
                if (searchResults.isNotEmpty()){
                    showSearchResults = true
                    currentBottomSheet = BookingTypeBottomSheet.PASSENGERS
                    isSheetOpen = true
                } else {
                    Toast.makeText(context,"No Such Route",Toast.LENGTH_LONG).show()
                }
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
                BookingTypeBottomSheet.RETURN_DATE ->sheetStateFullyExpanded
                BookingTypeBottomSheet.PASSENGERS -> sheetStatePartiallyExpanded
                BookingTypeBottomSheet.VEHICLE_TYPE -> sheetStateFullyExpanded
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

@OptIn(ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun MultiCityScreenPreview() {
    MultiCityBookingScreen(
        navController = rememberNavController(),
    )
}