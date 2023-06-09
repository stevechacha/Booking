package com.chacha.presentation.bottomnav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chacha.presentation.common.navigation.GraphDestinations.HOME_ROUTE
import com.chacha.presentation.common.navigation.GraphDestinations.MY_PROFILE_ROUTE
import com.chacha.presentation.common.navigation.GraphDestinations.MY_TRIPS_ROUTE
import com.chacha.presentation.common.navigation.GraphDestinations.PARCEL_ROUTE
import com.chacha.presentation.common.theme.BookingTheme


@SuppressLint("RememberReturnType")
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    val isShowBottomBar = when (currentDestination?.route) {
        HOME_ROUTE, MY_TRIPS_ROUTE,PARCEL_ROUTE, MY_PROFILE_ROUTE, null -> true
        else -> false
    }

    if (isShowBottomBar){
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            BottomBarDestination.values().asList().forEach { destination ->
                BottomNavigationItem(
                    selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                    icon = {
                        Icon(
                            painterResource(id = destination.icon),
                            contentDescription = destination.title,
                            modifier = Modifier.size(24.dp),
                            tint = if (currentDestination?.route == destination.route) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    },
                    label = {
                        Text(
                            text = destination.title,
                            fontSize = 10.sp,
                            color = if (currentDestination?.route == destination.route) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                            fontWeight = if (currentDestination?.route == destination.route) {
                                FontWeight.ExtraBold
                            } else {
                                FontWeight.Normal
                            },
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    alwaysShowLabel = true,
                    onClick = {
                        navController.navigate(destination.route) {
                            popUpTo(HOME_ROUTE) { inclusive = false }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }


}


@Preview
@Composable
fun BottomNavigationPreview() {
    BookingTheme {
        BottomNavigationBar(rememberNavController())
    }
}