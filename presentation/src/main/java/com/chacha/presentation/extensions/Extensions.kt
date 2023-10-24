package com.chacha.presentation.extensions

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar


@SuppressLint("NewApi")
fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

fun dateValidator(): (Long) -> Boolean {
    return { timeInMillis ->
        val currentCalendar = Calendar.getInstance()
        val endCalendarDate = Calendar.getInstance()

        // Set the current date to the beginning of the day
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0)
        currentCalendar.set(Calendar.MINUTE, 0)
        currentCalendar.set(Calendar.SECOND, 0)
        currentCalendar.set(Calendar.MILLISECOND, 0)

        // Set the end date 12 months from now
        endCalendarDate.timeInMillis = currentCalendar.timeInMillis
        endCalendarDate.add(Calendar.MONTH, 12)

        // Check if the given timeInMillis is within the next 12 months, starting from today
        timeInMillis >= currentCalendar.timeInMillis && timeInMillis <= endCalendarDate.timeInMillis
    }
}