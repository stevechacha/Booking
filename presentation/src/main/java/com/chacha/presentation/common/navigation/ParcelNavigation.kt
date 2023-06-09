package com.chacha.presentation.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.chacha.presentation.common.navigation.GraphDestinations.PARCEL_ROUTE
import com.chacha.presentation.parcel.ParcelScreen

fun NavGraphBuilder.parcelNavGraph(navController: NavController) {
    composable(route = PARCEL_ROUTE){
        ParcelScreen()
    }
}