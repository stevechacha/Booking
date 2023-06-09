package com.chacha.presentation.booking

import androidx.annotation.StringRes
import com.chacha.presentation.R
import kotlinx.coroutines.flow.Flow

sealed interface BookingEvent{
    object OnClick: BookingEvent
    data class OnClickWithParam(val param: String): BookingEvent
    data class OnEventPageChange(val settledPage: Int): BookingEvent
}

enum class BookingPages(@StringRes val title: Int) {
    ONEWAY(title = R.string.one_way),
    RETURN(title = R.string.returns),
    MULTI_CITY(title = R.string.multi_city)
}