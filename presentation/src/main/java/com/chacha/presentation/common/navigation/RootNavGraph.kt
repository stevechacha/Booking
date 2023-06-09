package com.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.chacha.presentation.booking.bookingNavGraph
import com.chacha.presentation.booking.departure_destination.departureDestinationNavGraph
import com.chacha.presentation.common.navigation.GraphDestinations.HOME_ROUTE
import com.chacha.presentation.common.navigation.GraphDestinations.ROOT


@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = ROOT,
        startDestination = HOME_ROUTE
    ) {
        authNavGraph(navController = navController)
        homeNavGraph(navController)
        bookingNavGraph(navController)
        myTripNavGraph(navController)
        parcelNavGraph(navController)
        profileNavGraph(navController)
        destinationNavGraph(navController, sheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed))
        departureDestinationNavGraph(navController, sheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed),)

    }

}


