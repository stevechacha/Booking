package com.chacha.presentation.book

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chacha.presentation.R
import com.chacha.presentation.book.components.BookCard
import com.chacha.presentation.book.components.DatePickerCard
import com.chacha.presentation.bus_seat.ChooseVehicles
import com.chacha.presentation.common.components.ContinueButton
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun OneWayBook(
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    var fromDeparture by remember { mutableStateOf("") }
    var toDestination by remember { mutableStateOf("") }

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    var bottomSheetContent by remember { mutableStateOf(1) }


    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
                Column {
                    Text(text = "Choose your departure", style = MaterialTheme.typography.labelMedium)
                }


        },
        sheetPeekHeight = 0.dp,
        backgroundColor = MaterialTheme.colorScheme.background

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {

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

            BookCard(
                fromTitle = stringResource(id = R.string.from),
                toTitle = stringResource(id = R.string.to),
                from = fromDeparture,
                fromHint = R.string.from_hint,
                toHint = R.string.to_hint,
                onFromClick = {
                    scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                },
                to = toDestination,
                onToClick = {
                    scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            val context = LocalContext.current


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

            ContinueButton(
                text = stringResource(id = R.string.continuee),
                onClick = {}
            )
        }

    }


}


