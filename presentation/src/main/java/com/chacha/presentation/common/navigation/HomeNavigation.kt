package com.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chacha.presentation.common.navigation.GraphDestinations.HOME_ROUTE
import com.chacha.presentation.home.HomeScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeNavGraph(navController: NavController){
    composable(route =HOME_ROUTE){
        HomeScreen()
    }
}