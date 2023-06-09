package com.chacha.presentation.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.chacha.presentation.common.navigation.GraphDestinations.MY_PROFILE_ROUTE
import com.chacha.presentation.profile.ProfileScreen

fun NavGraphBuilder.profileNavGraph(navController: NavController) {
    composable(route = MY_PROFILE_ROUTE){
        ProfileScreen()
    }
}