package com.chacha.presentation.bottomnav

import androidx.annotation.DrawableRes
import com.chacha.presentation.R
import com.chacha.presentation.common.navigation.GraphDestinations.BOOKING_ROUTE
import com.chacha.presentation.common.navigation.GraphDestinations.HOME_ROUTE
import com.chacha.presentation.common.navigation.GraphDestinations.MY_PROFILE_ROUTE
import com.chacha.presentation.common.navigation.GraphDestinations.MY_TRIPS_ROUTE
import com.chacha.presentation.common.navigation.GraphDestinations.PARCEL_ROUTE


enum class BottomBarDestination(val route: String, @DrawableRes val icon: Int, val title: String) {
    HOME(HOME_ROUTE, R.drawable.search_book, "Home"),
    BOOK(BOOKING_ROUTE, R.drawable.search_book, "Book"),
    MYTRIPS(MY_TRIPS_ROUTE, R.drawable.search_book, "My Trips"),
    PARCEL(PARCEL_ROUTE, R.drawable.search_book, "Parcel"),
    PROFILE(MY_PROFILE_ROUTE, R.drawable.profile_account, "Profile")

}