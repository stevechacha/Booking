package com.chacha.presentation.booking.booking_bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.VehicleType
import com.chacha.presentation.settings.ButtonRow


@Composable
fun BookingPassengerBottomSheet() {
    val bookingUiViewModel: BookingUiViewModel = viewModel()
    val bookingUiState by bookingUiViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        ButtonRow(text = "Hello", onClick = { /*TODO*/ })

    }
}


@Composable
fun VehicleSelect(
    vehicleType: VehicleType,
    isSelected: Boolean
) {


}
