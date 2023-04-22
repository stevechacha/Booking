package com.chacha.presentation.book.components

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chacha.presentation.R
import com.chacha.presentation.common.components.ContinueButton
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun Bookings() {

    var fromDeparture by remember { mutableStateOf("") }
    var toDestination by remember { mutableStateOf("") }

    val context = LocalContext.current

    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val date = remember { mutableStateOf<Date?>(null) }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val selectedDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }.time
            if (date.value == null) {
                date.value = selectedDate
            }
        },
        year, month, day
    )

    val pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    val showCurrentDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd MMM yyyy")
                .format(pickedDate)
        }
    }


    ModalBottomSheetLayout(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContent = {
            // implement bottom sheet when selecting below action but i want have diffrent content aand on seam able to reuse it
            // 1. when onFromClick open bottom sheet
            // 2. when onToClick open bottom sheet
            // 3. when onDepartureDateClick open bottom sheet
            // 4. when onReturnDateClick open bottom sheet
            // 5. when onPassengerClick open bottom sheet
            // 6. when onVehicleTypeClick open bottom sheet

            BookingBottomSheet(
                title = "Select From",
                items = listOf("Location A", "Location B", "Location C"),
                selectedItem = fromDeparture,
                onItemSelected = { from ->
                    fromDeparture = from
                    coroutineScope.launch {
                        modalBottomSheetState.hide()
                    }

                },
                onDismiss = {
                    coroutineScope.launch {
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
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
            ) {
                item {
                    BookCard(
                        fromTitle = stringResource(id = R.string.from),
                        toTitle = stringResource(id = R.string.to),
                        from = fromDeparture,
                        fromHint = R.string.from_hint,
                        toHint = R.string.to_hint,
                        onFromClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.show()
                            }
                        },
                        to = toDestination,
                        onToClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.show()
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(28.dp))


                    /// Date Picker Card
                    DatePickerCard(
                        departureTitle = stringResource(id = R.string.departure),
                        returnsTitle = stringResource(id = R.string.returns),
                        departureDate = if (date.value == null) showCurrentDate else DateFormat.getDateInstance()
                            .format(date.value!!),
                        returnDate = "",
                        numberOfPassenger = "1",
                        vehicleType = "Car",
                        onDepartureDateClick = { datePickerDialog.show() },
                        onReturnDateClick = { /*TODO*/ },
                        onPassengerClick = { /*TODO*/ },
                        onVehicleTypeClick = {}
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    /// Continue Button
                    ContinueButton(
                        text = stringResource(id = R.string.continuee),
                        onClick = {}
                    )


                }
            }

        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@OptIn(ExperimentalMaterialApi::class)
@Preview
fun PreviewBookings() {
    Bookings()

}