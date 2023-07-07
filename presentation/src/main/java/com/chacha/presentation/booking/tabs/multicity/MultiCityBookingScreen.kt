package com.chacha.presentation.booking.tabs.multicity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chacha.presentation.book.components.MultiCityCard
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.chacha.presentation.R
import com.chacha.presentation.booking.components.MultiCityBookingCard
import com.chacha.presentation.booking.components.MultiCityVehiclePicker
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingEvent
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingViewModel
import com.chacha.presentation.common.theme.PrimaryColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class,
    ExperimentalCoroutinesApi::class
)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MultiCityBookingScreen(navController: NavController,pagerState: PagerState) {
    var pickedDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember { derivedStateOf { DateTimeFormatter.ofPattern("dd MMM yyyy").format(pickedDate) } }
    val  oneWayBookingViewModel: OneWayBookingViewModel = viewModel()
    val oneWayBookingState by oneWayBookingViewModel.state.collectAsState()


    val fromDeparture by remember { mutableStateOf("") }
    val toDestination by remember { mutableStateOf("") }
    val dateDialogState = rememberMaterialDialogState()
    val context = LocalContext.current
    val currentDate = LocalDate.now()
    val  showCurrentDate = currentDate.toString()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        MultiCityBookingCard()
        MultiCityBookingCard()
        MultiCityVehiclePicker(
            oneWayBookingState = oneWayBookingState,
            onVehicleChanged = {
            } ,
            onSeatChanged = {
            },
            onBookClick = {
                navController.navigate("booking_details")
            },
        )

    }


}