package com.chacha.presentation.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chacha.presentation.common.navigation.GraphDestinations.HOME_ROUTE
import com.chacha.presentation.home.HomeScreen

fun NavGraphBuilder.homeNavGraph(navController: NavController){
    composable(route =HOME_ROUTE){
        HomeScreen()
    }
}