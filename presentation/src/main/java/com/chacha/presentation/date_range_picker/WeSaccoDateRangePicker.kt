package com.chacha.presentation.date_range_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.parsedDate
import kotlinx.coroutines.launch

data class SelectedDate(
    val startDateText: String,
    val endDateText: String,

    )

@Composable
fun WeSaccoDateRangePicker(
    state: DateRangePickerState,
    modifier: Modifier = Modifier,
    onSelectedDate: (SelectedDate) -> Unit,
    sheetState: SheetState,
    bookingUiViewModel: BookingUiViewModel = viewModel()

) {
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier.zIndex(1f))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /* dismiss the UI */ }) {
                Icon(Icons.Filled.Close, contentDescription = "Localized description")
            }
            TextButton(
                onClick = {
                    snackScope.launch {
                        sheetState.expand()
                    }
                    state.selectedStartDateMillis?.let {
                        state.selectedEndDateMillis?.parsedDate()?.let { it1 ->
                            BookingUiEvent.ReturnDateSelected(
                                departureDate = it.parsedDate(),
                                endDate = it1
                            )
                        }
                    }?.let {
                        bookingUiViewModel.handleUiEvent(
                            it
                        )
                    }


                },
                enabled = state.selectedEndDateMillis != null
            ) {
                Text(text = "Save")
            }
        }

        DateRangePicker(
            state = state, modifier = Modifier.weight(1f),
            showModeToggle = false,
            title = {
                Text(text = "Select Travel Date")
            }
        )
    }

}
