package com.chacha.presentation.booking.tabs.multicity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.parsedDate
import com.chacha.presentation.booking.components.MultiCityBookingCard
import com.chacha.presentation.booking.components.PassengerCardItem
import com.chacha.presentation.booking.components.VehicleCardItem
import com.chacha.presentation.booking.booking_bottom_sheet.BookingTypeBottomSheet
import com.chacha.presentation.booking.booking_bottom_sheet.BookingTypeBottomSheetLayout
import com.chacha.presentation.extensions.toMillis
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
    var skipPartiallyExpanded by remember { mutableStateOf(true) }
    val sheetState = androidx.compose.material3.rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var currentBottomSheet: BookingTypeBottomSheet? by remember { mutableStateOf(null) }
    val dateTime = LocalDateTime.now()

    val state = rememberDatePickerState()

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
                Column{
                   Text(text = "From")
                    TextField(
                        value = bookingUiState.departureDate,
                        onValueChange = {
                            state.selectedDateMillis?.let {
                                BookingUiEvent.DepartureDateSelected(
                                    it.parsedDate()
                                )
                            }?.let { bookingUiViewModel.handleUiEvent(it) }
                        },
                        label = { Text("Label") },
                        modifier = Modifier.clickable(MutableInteractionSource(),null){
                            isSheetOpen = true
                            currentBottomSheet = BookingTypeBottomSheet.ONE_BOOKING_DATE

                        },
                        placeholder = {
                            Text("hint")
                        },
                        enabled = false
                    )

                }

            },
            toContent = {
                Column{
                    Text(text = "To")
                    Text(
                        text = "" + "Select Destination",
                        modifier = Modifier.clickable(MutableInteractionSource(),null){
                            isSheetOpen = true
                            currentBottomSheet = BookingTypeBottomSheet.ONE_BOOKING_DATE

                        }
                    )
                }
            },
            dateContent = {
                Text(
                    text = " " + if(state.selectedDateMillis!=null){
                        state.selectedDateMillis?.let {
                            BookingUiEvent.DepartureDateSelected(
                                date = it.parsedDate()
                            )
                        }?.let { bookingUiViewModel.handleUiEvent(it) }
                    }
                    else dateTime.toMillis().parsedDate(),
                    modifier = Modifier.clickable(MutableInteractionSource(),null){
                        isSheetOpen = true
                        currentBottomSheet = BookingTypeBottomSheet.ONE_BOOKING_DATE

                    }
                )

            }
        )

        PassengerCardItem(
            bookingUiState = bookingUiState,
            onPassengerNumberChanged = {
                isSheetOpen = true
                currentBottomSheet = BookingTypeBottomSheet.PASSENGERS

            },
        )

        VehicleCardItem(
            bookingUiState = bookingUiState,
            onVehicleChanged = {
                isSheetOpen = true
                currentBottomSheet = BookingTypeBottomSheet.VEHICLE_TYPE
            },
        )

    }

    if (isSheetOpen) {
        WeBookingModalSheet(
            onDismissRequest = {
                isSheetOpen = false
            },
            sheetState = when(currentBottomSheet){
                BookingTypeBottomSheet.DEPARTURE -> androidx.compose.material3.rememberModalBottomSheetState(skipPartiallyExpanded = true)
                BookingTypeBottomSheet.DESTINATION -> androidx.compose.material3.rememberModalBottomSheetState(skipPartiallyExpanded = true)
                BookingTypeBottomSheet.ONE_BOOKING_DATE -> androidx.compose.material3.rememberModalBottomSheetState(skipPartiallyExpanded = true)
                BookingTypeBottomSheet.RETURN_DATE -> androidx.compose.material3.rememberModalBottomSheetState(skipPartiallyExpanded = true)
                BookingTypeBottomSheet.PASSENGERS -> androidx.compose.material3.rememberModalBottomSheetState(skipPartiallyExpanded = false)
                BookingTypeBottomSheet.VEHICLE_TYPE -> androidx.compose.material3.rememberModalBottomSheetState(skipPartiallyExpanded = false)
                null -> androidx.compose.material3.rememberModalBottomSheetState(skipPartiallyExpanded = true)
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