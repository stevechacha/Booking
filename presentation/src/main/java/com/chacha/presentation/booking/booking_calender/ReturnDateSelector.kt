package com.chacha.presentation.booking.booking_calender

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chacha.presentation.R
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.booking_calender.shared.ContinuousSelectionHelper.getSelection
import com.chacha.presentation.booking.booking_calender.shared.DateSelection
import com.chacha.presentation.booking.booking_calender.shared.displayText
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale


private val primaryColor = Color.Black.copy(alpha = 0.9f)
private val selectionColor = primaryColor
private val continuousSelectionColor = primaryColor.copy(alpha = 0.3f)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReturnDateSelector(
    close: () -> Unit = {},
    dateSelected: (startDate: LocalDate, endDate: LocalDate) -> Unit = { _, _ -> },
) {
    val bookingUiViewModel: BookingUiViewModel = viewModel()
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth }
    val endMonth = remember { currentMonth.plusMonths(12) }
    val today = remember { LocalDate.now() }
    var selection by remember { mutableStateOf(DateSelection()) }
    val daysOfWeek = remember { daysOfWeek() }
//    StatusBarColorUpdateEffect(Color.White)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column {
            val state = rememberCalendarState(
                startMonth = startMonth,
                endMonth = endMonth,
                firstVisibleMonth = currentMonth,
                firstDayOfWeek = daysOfWeek.first(),
            )
            CalendarTop(
                daysOfWeek = daysOfWeek,
                selection = selection,
                close = close,
                clearDates = { selection = DateSelection() },
            )
            VerticalCalendar(
                state = state,
                contentPadding = PaddingValues(bottom = 100.dp),
                dayContent = { value ->
                    Day(
                        value,
                        today = today,
                        selection = selection,
                    ) { day ->
                        if (day.position == DayPosition.MonthDate &&
                            (day.date == today || day.date.isAfter(today))
                        ) {
                            selection = getSelection(
                                clickedDate = day.date,
                                dateSelection = selection,
                            )
                        }
                    }
                },
                monthHeader = { month -> MonthHeader(month) },
            )
        }
        CalendarBottom(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(Color.White)
                .align(Alignment.BottomCenter),
            selection = selection,
            save = {
                val formattedStartDate = selection.formattedStartDate
                val formattedEndDate = selection.formattedEndDate

                val (startDate, endDate) = selection
                if (startDate != null && endDate != null) {
                    dateSelected(startDate, endDate)
                    if (formattedStartDate != null) {
                        bookingUiViewModel.handleUiEvent(
                            BookingUiEvent.ReturnDateSelected(
                                departureDate = startDate,
                                returnDate = endDate
                            )
                        )
                    }
                }

                bookingUiViewModel.handleUiEvent(
                    BookingUiEvent.DepartureDateSelected(
                        date = startDate!!,
                    )
                )
                close()
            }
        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun Day(
    day: CalendarDay,
    today: LocalDate,
    selection: DateSelection,
    onClick: (CalendarDay) -> Unit,
) {
    var textColor = Color.Transparent
    Box(
        modifier = Modifier
            .aspectRatio(1f) // This is important for square-sizing!
            .clickable(
                enabled = day.position == DayPosition.MonthDate && day.date >= today,
                showRipple = false,
                onClick = { onClick(day) },
            )
            .backgroundHighlight(
                day = day,
                today = today,
                selection = selection,
                selectionColor = selectionColor,
                continuousSelectionColor = continuousSelectionColor,
            ) { textColor = it },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MonthHeader(calendarMonth: CalendarMonth) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = calendarMonth.yearMonth.displayText(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CalendarTop(
    modifier: Modifier = Modifier,
    daysOfWeek: List<DayOfWeek>,
    selection: DateSelection,
    close: () -> Unit,
    clearDates: () -> Unit,
) {
    Column(modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                modifier = Modifier.height(IntrinsicSize.Max),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .clickable(onClick = close)
                        .padding(12.dp),
                    painter = painterResource(id = R.drawable.outline_close_24),
                    contentDescription = "Close",
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(percent = 50))
                        .clickable(onClick = clearDates)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = "Clear",
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End,
                )
            }
            val daysBetween = selection.daysBetween
            val text = if (daysBetween == null) {
                "Select dates"
            } else {
                // Ideally you'd do this using the strings.xml file
                "$daysBetween ${if (daysBetween == 1L) "day" else "days"} in Travel"
            }
            Text(
                modifier = Modifier.padding(horizontal = 14.dp),
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
            ) {
                for (dayOfWeek in daysOfWeek) {
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray,
                        text = dayOfWeek.displayText(),
                        fontSize = 15.sp,
                    )
                }
            }
        }
        Divider()
    }
}

@SuppressLint("NewApi")
 val dateFormatterr: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CalendarBottom(
    modifier: Modifier = Modifier,
    selection: DateSelection,
    save: () -> Unit,
) {
    // Format and display the selected dates
    val formattedStartDate = selection.startDate?.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH))
    val formattedEndDate = selection.endDate?.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH))

    Column(modifier.fillMaxWidth()) {
        Divider()
        if (formattedStartDate != null && formattedEndDate != null) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                text = "$formattedStartDate - $formattedEndDate",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )
        }
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                onClick = save,
                enabled = selection.daysBetween != null,
            ) {
                Text(text = "Save")
            }
        }



    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(heightDp = 800)
@Composable
private fun Example2Preview() {
    ReturnDateSelector()
}