package com.chacha.presentation.booking.tabs.one_way

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chacha.presentation.R
import com.chacha.presentation.book.components.BookCard
import com.chacha.presentation.book.components.BookingBottomSheet
import com.chacha.presentation.book.components.DatePickerCard
import com.chacha.presentation.booking.components.BookingCard
import com.chacha.presentation.booking.components.DateBookingCard
import com.chacha.presentation.common.components.ContinueButton
import com.chacha.presentation.common.navigation.GraphDestinations
import com.chacha.presentation.common.navigation.GraphDestinations.DEPARTURE_DESTINATION
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun OneWayBookingScreen(navController: NavController, pagerState: PagerState) {
    var fromDeparture by remember { mutableStateOf("") }
    var toDestination by remember { mutableStateOf("") }
    var showDepartureList by remember { mutableStateOf(false) }
    var showDestinationList by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded)
    val coroutineScope = rememberCoroutineScope()
    val pickedDate by remember { mutableStateOf(LocalDate.now()) }
    val showCurrentDate by remember { derivedStateOf { DateTimeFormatter.ofPattern("dd MMM yyyy").format(pickedDate) } }

    val  oneWayBookingViewModel : OneWayBookingViewModel = viewModel()
    val oneWayState by oneWayBookingViewModel.state.collectAsState()

    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize().padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        BookingCard(
            oneWayBookingState =  oneWayState,
            onFromClick = {
              navController.navigate(DEPARTURE_DESTINATION)
            } ,
            onToClick = {

            },
            navController = navController
        )

        DateBookingCard(
            oneWayBookingState = oneWayState,
            onDateChanged = {},
            onTimeChanged = {} ,
            onFareChanged = {} ,
            onVehicleChanged = {} ,
            onSeatChanged = {},
            onBookClick = {}
        )

        ContinueButton(
            text = stringResource(id = R.string.continuee),
            onClick = {}
        )
    }

    if(showDepartureList) {
        ModalBottomSheetLayout(
            sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
            sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded),
            sheetContent = {
                BookingBottomSheet(
                    title = "Select From",
                    items = listOf("Location A", "Location B", "Location C"),
                    selectedItem = fromDeparture,
                    onItemSelected = { from ->
                        fromDeparture = from
                        showDepartureList = false
                        scope.launch {
                            modalBottomSheetState.hide()
                        }

                    },
                    onDismiss = {
                        scope.launch {
                            modalBottomSheetState.hide()
                        }
                    },
                    onItemToSelected = { to->
                        toDestination = to
                        coroutineScope.launch {
                            modalBottomSheetState.hide()
                        }

                    },
                )

            }
        ) {
            // Empty content
        }
    }
    if(showDestinationList) {
        ModalBottomSheetLayout(
            sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
            sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded),
            sheetContent = {
                BookingBottomSheet(
                    title = "Select From",
                    items = listOf("Location A", "Location B", "Location C"),
                    selectedItem = toDestination,
                    onItemSelected = { to ->
                        toDestination = to
                        showDestinationList = false


                    },
                    onDismiss = {
                        scope.launch {
                            modalBottomSheetState.hide()
                        }
                    },
                    onItemToSelected = { to->
                        toDestination = to
                        coroutineScope.launch {
                            modalBottomSheetState.hide()
                        }

                    },
                )

            }
        ) {
            // Empty content
        }
    }


}