package com.chacha.presentation.trips

import androidx.annotation.StringRes
import com.chacha.presentation.R
import kotlinx.coroutines.flow.Flow

sealed interface MyTripEvent{
    object OnClick: MyTripEvent
    data class OnClickWithParam(val param: String): MyTripEvent
    data class OnEventPageChange(val settledPage: Int): MyTripEvent
}

enum class MyTripPages(@StringRes val title: Int) {
    UPCOMING(title = R.string.upcoming_trip),
    COMPLETED(title = R.string.completed_trips),
    CANCELLED(title = R.string.cancelled_trips)
}