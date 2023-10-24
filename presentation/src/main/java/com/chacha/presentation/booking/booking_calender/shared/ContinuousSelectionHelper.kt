package com.chacha.presentation.booking.booking_calender.shared

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.nextMonth
import com.kizitonwose.calendar.core.previousMonth
import com.kizitonwose.calendar.core.yearMonth
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale
import kotlin.LazyThreadSafetyMode.NONE

@RequiresApi(Build.VERSION_CODES.O)
data class DateSelection(
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)
) {
    @RequiresApi(Build.VERSION_CODES.O)
    val formattedStartDate: String? = startDate?.format(dateFormatter)
    val formattedEndDate: String? = endDate?.format(dateFormatter)
    val daysBetween by lazy(NONE) {
        if (startDate == null || endDate == null) null else {
            ChronoUnit.DAYS.between(startDate, endDate)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
data class OneWayDateSelection(
    val startDate: LocalDate? = null,
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)
) {
    @RequiresApi(Build.VERSION_CODES.O)
    val formattedStartDate: String? = startDate?.format(dateFormatter)
    val daysBetween: Long? = null  // Remove daysBetween property

    // Optional: Add a helper function to check if a date is selected
    fun isDateSelected(): Boolean {
        return startDate != null
    }
}


@RequiresApi(Build.VERSION_CODES.O)
private val rangeFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
@RequiresApi(Build.VERSION_CODES.O)
fun dateRangeDisplayText(startDate: LocalDate, endDate: LocalDate): String {
    return "Selected: ${rangeFormatter.format(startDate)} - ${rangeFormatter.format(endDate)}"
}
object ContinuousSelectionHelper {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getSelection(
        clickedDate: LocalDate,
        dateSelection: DateSelection,
    ): DateSelection {
        val (selectionStartDate, selectionEndDate) = dateSelection
        return if (selectionStartDate != null) {
            if (clickedDate < selectionStartDate || selectionEndDate != null) {
                DateSelection(startDate = clickedDate, endDate = null)
            } else if (clickedDate != selectionStartDate) {
                DateSelection(startDate = selectionStartDate, endDate = clickedDate)
            } else {
                DateSelection(startDate = clickedDate, endDate = null)
            }
        } else {
            DateSelection(startDate = clickedDate, endDate = null)
        }
    }

    @SuppressLint("NewApi")
    fun getOneSelection(
        clickedDate: LocalDate,
        dateSelection: OneWayDateSelection,
    ): OneWayDateSelection {
        val selectionStartDate = dateSelection.startDate
        return if (selectionStartDate != null) {
            if (clickedDate != selectionStartDate) {
                OneWayDateSelection(startDate = clickedDate)
            } else {
                OneWayDateSelection(startDate = clickedDate)
            }
        } else {
            OneWayDateSelection(startDate = clickedDate)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isInDateBetweenSelection(
        inDate: LocalDate,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Boolean {
        if (startDate.yearMonth == endDate.yearMonth) return false
        if (inDate.yearMonth == startDate.yearMonth) return true
        val firstDateInThisMonth = inDate.yearMonth.nextMonth.atStartOfMonth()
        return firstDateInThisMonth in startDate..endDate && startDate != firstDateInThisMonth
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isOutDateBetweenSelection(
        outDate: LocalDate,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Boolean {
        if (startDate.yearMonth == endDate.yearMonth) return false
        if (outDate.yearMonth == endDate.yearMonth) return true
        val lastDateInThisMonth = outDate.yearMonth.previousMonth.atEndOfMonth()
        return lastDateInThisMonth in startDate..endDate && endDate != lastDateInThisMonth
    }
}

