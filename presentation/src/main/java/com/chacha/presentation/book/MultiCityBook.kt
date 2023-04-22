package com.chacha.presentation.book

import android.os.Build
import android.widget.Toast
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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chacha.presentation.book.components.MultiCityCard
import androidx.compose.ui.window.DialogProperties
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import com.chacha.presentation.R
import com.chacha.presentation.common.theme.PrimaryColor
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.Builder.dateRangePicker
import java.text.SimpleDateFormat


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MultiCityBook() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var pickedDate by remember {
            mutableStateOf(LocalDate.now())
        }
        val formattedDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("dd MMM yyyy")
                    .format(pickedDate)
            }
        }
        val fromDeparture by remember { mutableStateOf("") }
        val toDestination by remember { mutableStateOf("") }
        val dateDialogState = rememberMaterialDialogState()
        val context = LocalContext.current
        val currentDate = LocalDate.now()
        val  showCurrentDate = currentDate.toString()


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            MultiCityCard(
                flightNumber = stringResource(id = R.string.flight_one),
                departureHint = R.string.from_hint,
                destinationHint = R.string.to_hint,
                flightOneDeparture = fromDeparture ,
                flightOneDestination = toDestination,
                showCurrentDate = showCurrentDate ,
                flightOneDate = formattedDate ,
                onDateFlightOneClick = {
                    dateDialogState.show()
                },
                onFlightOneDepartureClick = { /*TODO*/ }) {

            }
            MaterialDialog(
                dialogState = dateDialogState,
                buttons = {
                    positiveButton(text = "Ok") {
                        dateDialogState.hide()
                    }
                    negativeButton(text = "Cancel")
                },
                backgroundColor = PrimaryColor
            ) {
                datepicker(
                    initialDate = LocalDate.now(),
                    title = "Pick a date",
                    allowedDateValidator = {
                        it.isAfter(LocalDate.now())
                    },
                    ) {
                    pickedDate = it
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {

            MultiCityCard(
                flightNumber = stringResource(id = R.string.flight_two),
                departureHint = R.string.from_hint,
                destinationHint = R.string.to_hint,
                flightOneDeparture = fromDeparture ,
                flightOneDestination = toDestination,
                showCurrentDate = showCurrentDate ,
                flightOneDate = formattedDate ,
                onDateFlightOneClick = {
                    dateDialogState.show()
                },
                onFlightOneDepartureClick = { /*TODO*/ }) {

            }
            MaterialDialog(
                dialogState = dateDialogState,
                buttons = {
                    positiveButton(text = "Ok") {
                        dateDialogState.hide()
                    }
                    negativeButton(text = "Cancel")
                }
            ) {
                datepicker(
                    initialDate = LocalDate.now().plusDays(3),
                    title = "Pick a date",
                    allowedDateValidator = {
                        it.isAfter(LocalDate.now())
                    }
                    ) {
                    pickedDate = it
                }
            }
        }

    }
}


