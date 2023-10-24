package com.chacha.presentation.booking.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
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
import com.chacha.presentation.booking.departure_result.DepartureResultList
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingScreen
import com.chacha.presentation.common.navigation.GraphDestinations.BOOKING_ROUTE
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.bookingNavGraph(navController: NavHostController) {
    composable(route = BOOKING_ROUTE) { backStackEntry ->
        val bookingUiViewModel =
            backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        BookingScreen(
            navController = navController,
            bookingUiViewModel = bookingUiViewModel,
        )
    }

    composable(
        route = BookingNavigationScreen.OneWayRoute.route
    )
    { backStackEntry ->
        val bookingUiViewModel =
            backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        OneWayBookingScreen(
            navController = navController,
            bookingUiViewModel = bookingUiViewModel,
        )
    }

    /*composable(
        route = SEARCH_RESULT,
    ) { backStackEntry ->
        val bookingUiViewModel = backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        val uiState by bookingUiViewModel.uiState.collectAsState()
        val departure = uiState.departurePlace
        val departureDate = uiState.departureDate
        val destination = uiState.destinationPlace
        val searchResults = bookingUiViewModel.searchFlights(departure, destination, departureDate)

        SearchRoutesResultsContent(
            searchResults = searchResults,
            onClick = { searchResult->
                navController.navigate("weather/${searchResult.destination.city}/${searchResult.departure.code}/${searchResult.departure.city}")

            },
            navController = navController
        )
    }*/
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
        )) {
        DepartureResultList(
            navController = navController,
            destinationPlace = it.arguments?.getString("destination") ?: "",
            destinationCode = it.arguments?.getString("destinationCode") ?: "",
            departurePlace = it.arguments?.getString("departurePlace") ?: "",
            departureCode = it.arguments?.getString("departureCode") ?: "",
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


