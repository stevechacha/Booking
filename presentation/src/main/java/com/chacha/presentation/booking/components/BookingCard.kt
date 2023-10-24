package com.chacha.presentation.booking.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chacha.presentation.R
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiState
import com.chacha.presentation.booking.BookingUiViewModel


@Composable
fun BookingCard(
    bookingUiState: BookingUiState,
    onFromClick: (String) -> Unit,
    onToClick: (String) -> Unit,
    bookingUiViewModel: BookingUiViewModel = viewModel()
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AppTextField(
                value = bookingUiState.departurePlace,
                onValueChange = { departure->
                    onFromClick(departure)
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.DeparturePlaceSelected(departure))
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.DeparturePickupStationSelected(departure))

                },
                title = "From",
                hint = "Select Departure",
                hint2 = bookingUiState.departurePickupStation

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth(0.91f),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_interchange),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )

            }


            AppTextField(
                value = bookingUiState.destinationPlace,
                title = "To",
                hint = "Select Destination",
                onValueChange = { destination->
                    onToClick(destination)
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.DestinationPlaceSelected(destination))
                },
                modifier = Modifier.fillMaxWidth()

            )


        }

    }

}

@Preview
@Composable
fun BookingCardPreview() {
    BookingCard(
        bookingUiState = BookingUiState(),
        onFromClick = {},
        onToClick = {},
    )
}