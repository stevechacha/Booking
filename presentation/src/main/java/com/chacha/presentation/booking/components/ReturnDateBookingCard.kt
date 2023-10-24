package com.chacha.presentation.booking.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chacha.presentation.booking.BookingUiState
import com.chacha.presentation.booking.booking_calender.dateFormatterr


@SuppressLint("NewApi")
@Composable
fun ReturnDateBookingCard(
    bookingUiState: BookingUiState,
    onDepartureDateChanged: (String) -> Unit,
    onReturnDateChanged: (String) -> Unit,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .wrapContentHeight(),
    ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                // Add constraints for the first row
                val (departureDateField, returnDateField) = createRefs()
                AppTextField(
                    value = dateFormatterr.format(bookingUiState.departureDate).toString(),
                    onValueChange = { departureDate ->
                        onReturnDateChanged(departureDate)
                    },
                    title = "Departure",
                    hint = "Select Date",
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(departureDateField) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                AppTextField(
                    value = dateFormatterr.format(bookingUiState.returnDate).toString(),
                    onValueChange = { returnDate ->
                        onReturnDateChanged(returnDate)
                    },
                    title = "Returns",
                    hint = "Select Date",
                    modifier = Modifier
                        .fillMaxWidth(0.56f)
                        .padding(end = 10.dp)
                        .constrainAs(returnDateField) {
                            top.linkTo(departureDateField.top)
                            start.linkTo(departureDateField.end)
                            bottom.linkTo(departureDateField.bottom)
                            end.linkTo(parent.end)
                        }
                )



        }
    }
}


@SuppressLint("NewApi")
@Composable
@Preview
fun ReturningDatePreview() {
    ReturnDateBookingCard(
        bookingUiState = BookingUiState(),
        onDepartureDateChanged = {},
        onReturnDateChanged = {},
    )

}