package com.chacha.presentation.booking.booking_bottom_sheet

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.parsedDate
import com.chacha.presentation.common.components.ContinueButton
import com.chacha.presentation.extensions.dateValidator
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Calendar


@SuppressLint("NewApi")
@Composable
fun SingleBookingDatePicker(
    onDateRangePickerState: () -> Unit,
    closeSheet: () -> Unit,
    bookingUiViewModel: BookingUiViewModel = viewModel()
) {
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    SnackbarHost(hostState = snackState, Modifier.zIndex(1f))

    val dateTime = LocalDateTime.now()

    val state = rememberDatePickerState()
    val isSaveButtonVisible =
        state.selectedDateMillis != null // Determine visibility of the "Save" button


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
            IconButton(onClick = { closeSheet() }) {
                Icon(Icons.Filled.Close, contentDescription = "Localized description")
            }
        }

        DatePicker(
            state = state,
            modifier = Modifier.weight(1f),
            dateFormatter = DatePickerFormatter(),
            showModeToggle = false,
            dateValidator = dateValidator(),
            title = {
                Text(
                    text = "Select Travel Date",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )


        ContinueButton(
            onClick = {
                closeSheet()
                snackScope.launch {
                    sheetState.hide()
                }
                state.selectedDateMillis?.let {
                    BookingUiEvent.DepartureDateSelected(
                        date = it.parsedDate()
                    )
                }?.let { bookingUiViewModel.handleUiEvent(it) }

            },
            enabled = isSaveButtonVisible,
            text = "Save"

        )

    }

}