package com.chacha.presentation.pooking

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chacha.presentation.R
import com.chacha.presentation.book.components.BookCard
import com.chacha.presentation.book.components.BookingBottomSheet
import com.chacha.presentation.book.components.DatePickerCard
import com.chacha.presentation.common.components.ContinueButton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(
    ExperimentalMaterialApi::class, ExperimentalFoundationApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun Pooking(
    viewModel: PookingViewModel,
    onEvent: (PookingEvent)-> Unit
) {
    var fromDeparture by remember { mutableStateOf("") }
    var toDestination by remember { mutableStateOf("") }
    var vehicleType by remember { mutableStateOf("") }
    var numberOfPassenger by remember { mutableStateOf(0) }


    val context = LocalContext.current
    val state by viewModel.pookingState.collectAsState()




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
    val event = remember {
        mutableStateOf(PookingEvent.From)
    }

    ModalBottomSheetLayout(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContent = {
            viewModel.onEvent(PookingEvent.From)

        }

    ) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    BookCard(
                        fromTitle = stringResource(id = R.string.from),
                        toTitle = stringResource(id = R.string.to),
                        from = fromDeparture,
                        fromHint = R.string.from_hint,
                        toHint = R.string.to_hint,
                        onFromClick = {
                            onEvent(PookingEvent.From)
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

                    Spacer(modifier = Modifier.height(30.dp))


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




