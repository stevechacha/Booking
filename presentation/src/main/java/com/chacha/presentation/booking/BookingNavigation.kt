package com.chacha.presentation.booking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chacha.presentation.common.navigation.GraphDestinations.BOOKING_ROUTE


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.bookingNavGraph(navController: NavController) {
    composable(route = BOOKING_ROUTE){
        BookingScreen(navController)
    }
}