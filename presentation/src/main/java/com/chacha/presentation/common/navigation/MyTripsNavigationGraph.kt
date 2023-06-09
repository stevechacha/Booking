package com.chacha.presentation.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chacha.presentation.common.navigation.GraphDestinations.MY_TRIPS_ROUTE
import com.chacha.presentation.trips.MyTripScreen


fun NavGraphBuilder.myTripNavGraph(navController: NavController){
    composable(route = MY_TRIPS_ROUTE){
        MyTripScreen()

    }

}