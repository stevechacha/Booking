package com.chacha.presentation.booking.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chacha.presentation.booking.BookingScreen
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.bus_seats.BookingConfirmScreen
import com.chacha.presentation.booking.bus_seats.BusSeat
import com.chacha.presentation.booking.bus_seats.SeatDetailsScreen
import com.chacha.presentation.booking.departure_result.DepartureResultList
import com.chacha.presentation.booking.model.SeatDetails
import com.chacha.presentation.booking.model.SeatDetailsEntry
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingScreen
import com.chacha.presentation.common.navigation.GraphDestinations.BOOKING_ROUTE


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.bookingNavGraph(navController: NavHostController) {
    composable(route = BOOKING_ROUTE) { backStackEntry ->
        val bookingUiViewModel = backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        val bookingUiState by bookingUiViewModel.uiState.collectAsState()

        BookingScreen(
            navController = navController,
            bookingUiViewModel = bookingUiViewModel,
        )
    }

    composable(
        route = BookingNavigationScreen.OneWayRoute.route
    )
    { backStackEntry ->
        val bookingUiViewModel = backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        val bookingUiState by bookingUiViewModel.uiState.collectAsState()
        OneWayBookingScreen(
            navController = navController,
            bookingUiViewModel = bookingUiViewModel,

        )
    }

    composable("weather/{destination}/{destinationCode}/{departurePlace}/{departureCode}",
        arguments = listOf(
            navArgument("destination") {
                type = NavType.StringType
            },
            navArgument("destinationCode") {
                type = NavType.StringType
            },
            navArgument("departureCode") {
                type = NavType.StringType
            },
            navArgument("departurePlace") {
                type = NavType.StringType
            }
        )) {  backStackEntry->
        val bookingUiViewModel = backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        val bookingUiState by bookingUiViewModel.uiState.collectAsState()
        DepartureResultList(
            navController = navController,
            bookingUiState = bookingUiState
        )
    }

    composable("bus/{destination}/{destinationCode}/{departurePlace}/{departureCode}",
        arguments = listOf(
            navArgument("destination") {
                type = NavType.StringType
            },
            navArgument("destinationCode") {
                type = NavType.StringType
            },
            navArgument("departureCode") {
                type = NavType.StringType
            },
            navArgument("departurePlace") {
                type = NavType.StringType
            }
        ))  {  backStackEntry->
        val bookingUiViewModel = backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        val bookingUiState by bookingUiViewModel.uiState.collectAsState()
        BusSeat(
            navController = navController,
            bookingUiState = bookingUiState
        )
    }

    composable(
        "seatDetails/{numSeats}/{seatLabel0}/{destination}/{destinationCode}/{departurePlace}/{departureCode}",
        arguments = listOf(
            navArgument("numSeats") { type = NavType.IntType },
            // Add more arguments for seat labels if needed
            navArgument("seatLabel0") { type = NavType.StringType },
            navArgument("destination") {
                type = NavType.StringType
            },
            navArgument("destinationCode") {
                type = NavType.StringType
            },
            navArgument("departureCode") {
                type = NavType.StringType
            },
            navArgument("departurePlace") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val bookingUiViewModel = backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        val bookingUiState by bookingUiViewModel.uiState.collectAsState()
        val numSeats = backStackEntry.arguments?.getInt("numSeats") ?: 0
        val seatDetailsList = mutableListOf<SeatDetails>()
        for (i in 0 until numSeats) {
            val seatLabel = backStackEntry.arguments?.getString("seatLabel$i") ?: ""
            seatDetailsList.add(SeatDetails(i, seatLabel, SeatDetailsEntry("", "", "", "")))
        }
        SeatDetailsScreen(
            navController = navController,
            numSeats = numSeats,
            seatLabels = "",
            bookingUiState = bookingUiState
        )
    }

    /*composable("confirm/{contact}/{name}/{id}",
        arguments = listOf(
            navArgument("contact") {
                type = NavType.StringType
            },
            navArgument("name") {
                type = NavType.StringType
            },
            navArgument("id") {
                type = NavType.StringType
            },
        )) {  backStackEntry->
        val bookingUiViewModel = backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        val bookingUiState by bookingUiViewModel.uiState.collectAsState()
        BookingConfirmScreen(
            navController = navController,
            bookingUiState = bookingUiState
        )
    }*/

    composable("confirm") {  backStackEntry->
        val bookingUiViewModel = backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        val bookingUiState by bookingUiViewModel.uiState.collectAsState()
        BookingConfirmScreen(
            navController = navController,
            bookingUiState = bookingUiState
        )
    }


}


sealed class BookingNavigationScreen(val route: String) {
    object OneWayRoute : BookingNavigationScreen("one_way")
    object RoundWayRoute : BookingNavigationScreen("round_way")
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.bookingUIViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}


