package com.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.composable
import com.chacha.presentation.activity.MainActivity
import com.chacha.presentation.common.navigation.GraphDestinations.MY_PROFILE_ROUTE
import com.chacha.presentation.common.navigation.GraphDestinations.SETTINGS_SHEET
import com.chacha.presentation.profile.ProfileScreen
import com.chacha.presentation.settings.Settings

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.profileNavGraph(navController: NavController) {
    composable(route = MY_PROFILE_ROUTE){
        ProfileScreen(navController)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.appSettingsNavGraph(navController: NavController) {
    composable(route = SETTINGS_SHEET){
        Settings()
    }
}