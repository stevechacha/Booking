package com.chacha.presentation.booking.booking_bottom_sheet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chacha.presentation.booking.ANIMATED_CONTENT_ANIMATION_DURATION
import com.chacha.presentation.booking.booking_destination_list.BookingDepartureList
import com.chacha.presentation.booking.booking_destination_list.BookingDestinationList
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.booking_destination_list.BusBooking
import com.chacha.presentation.booking.booking_search_route.SearchRoutesResultsContent
import com.chacha.presentation.booking.booking_calender.OneWayDateSelector
import com.chacha.presentation.booking.booking_calender.ReturnDateSelector

@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingTypeBottomSheetLayout(
    bookingTypeBottomSheet: BookingTypeBottomSheet,
    navController: NavController,
    onItemSelected: () -> Unit,
    closeSheet: () -> Unit,
    bookingUiViewModel: BookingUiViewModel = viewModel(),
) {

    val bookingUiState by bookingUiViewModel.uiState.collectAsState()
    val departure = bookingUiState.departurePlace
    val destination = bookingUiState.destinationPlace
    val departureDate = bookingUiState.departureDate
    var searchResults by remember { mutableStateOf<List<BusBooking>>(emptyList()) }
    searchResults = bookingUiViewModel.searchFlights(departure, destination, departureDate.toString())

    AnimatedContent(
        targetState = bookingTypeBottomSheet,
        label = "",
        transitionSpec = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(ANIMATED_CONTENT_ANIMATION_DURATION, easing =  EaseIn)
            ) with slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(ANIMATED_CONTENT_ANIMATION_DURATION , easing = EaseOut)
            )
        },
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

            BookingTypeBottomSheet.ONE_BOOKING_DATE -> OneWayDateSelector(close = closeSheet)
            BookingTypeBottomSheet.RETURN_DATE -> ReturnDateSelector(close = closeSheet)
            BookingTypeBottomSheet.PASSENGERS -> SearchRoutesResultsContent(
                searchResults = searchResults,
                onClick = {searchResults->
                    navController.navigate("weather/${searchResults.destination.city}/${searchResults.destination.code}/${searchResults.departure.city}/${searchResults.departure.code}")
                      closeSheet()
                          },
                navController = navController,


            )
            BookingTypeBottomSheet.VEHICLE_TYPE -> BookingVehicleTypeBottomSheet(onClose = closeSheet)
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