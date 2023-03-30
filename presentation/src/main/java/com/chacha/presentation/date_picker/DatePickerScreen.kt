package com.chacha.presentation.date_picker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.remember
import java.time.LocalDateTime
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerScreen() {

    val dateTime = LocalDateTime.now()

    val datePickerState = remember {
        DatePickerState(
            yearRange = (2023..2024),
            initialSelectedDateMillis = dateTime.toMillis(),
            initialDisplayMode = DisplayMode.Picker,
            initialDisplayedMonthMillis = null
        )
    }



    DatePicker(state = datePickerState)
    Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateRangePickerScreen() {

    val dateTime = LocalDateTime.now()

    val dateRangePickerState = remember {
        DateRangePickerState(
            initialSelectedStartDateMillis = dateTime.toMillis(),
            initialDisplayedMonthMillis = null,
            initialSelectedEndDateMillis = dateTime.plusDays(3).toMillis(),
            initialDisplayMode = DisplayMode.Picker,
            yearRange = (2023..2024)
        )
    }

    DateRangePicker(state = dateRangePickerState)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()