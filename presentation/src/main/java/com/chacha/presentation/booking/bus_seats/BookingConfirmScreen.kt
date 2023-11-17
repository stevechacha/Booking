package com.chacha.presentation.booking.bus_seats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chacha.presentation.booking.BookingUiState
import com.chacha.presentation.booking.model.SeatDetailsEntry

@Composable
fun BookingConfirmScreen(
    navController: NavController,
    bookingUiState: BookingUiState
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val passengerContact = navBackStackEntry?.arguments?.getString("contact") ?: ""
    val passengerName = navBackStackEntry?.arguments?.getString("name") ?: ""
    val passengerId = navBackStackEntry?.arguments?.getString("id") ?: ""
//    val passengerGender = navBackStackEntry?.arguments?.getString("gender") ?: ""

    val seatDetails = remember { mutableStateListOf<SeatDetailsEntry>() }

    seatDetails.add(SeatDetailsEntry(passengerName,passengerId,passengerContact,"passengerGender"))
    Column {
        LazyColumn(){
            items(seatDetails.size){
                Text(text = it.toString())
            }
        }

    }

}