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
import androidx.navigation.compose.composable
import com.chacha.presentation.booking.BookingScreen
import com.chacha.presentation.booking.booking_search_route.SearchResultsScreen
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingScreen
import com.chacha.presentation.common.navigation.GraphDestinations.BOOKING_ROUTE
import com.chacha.presentation.common.navigation.GraphDestinations.SEARCH_RESULT
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.bookingNavGraph(navController: NavHostController) {
    composable(route = BOOKING_ROUTE){ backStackEntry ->
        val bookingUiViewModel = backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        BookingScreen(
            navController = navController,
            bookingUiViewModel = bookingUiViewModel,
        )
    }

    composable(
        route = BookingNavigationScreen.OneWayRoute.route)
    {  backStackEntry ->
        val bookingUiViewModel = backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        OneWayBookingScreen(
            navController = navController,
            bookingUiViewModel = bookingUiViewModel,
            )
    }

    composable(
        route = SEARCH_RESULT,
    ) { backStackEntry ->
        val bookingUiViewModel = backStackEntry.bookingUIViewModel<BookingUiViewModel>(navController)
        SearchResultsScreen(
            bookingUiViewModel = bookingUiViewModel

        )
    }
}


sealed class BookingNavigationScreen(val route: String){
    object OneWayRoute: BookingNavigationScreen("one_way")
    object RoundWayRoute: BookingNavigationScreen("round_way")
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


