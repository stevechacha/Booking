package com.chacha.presentation.booking.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chacha.presentation.R
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiState
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.booking_component.FromUserInput
import com.chacha.presentation.booking.booking_component.ToDestinationUserInput
import com.chacha.presentation.booking.booking_component.ToUserInput


@Composable
fun BookingCard(
    bookingUiState: BookingUiState,
    onFromClick: (String) -> Unit,
    onToClick: (String) -> Unit,
    bookingUiViewModel: BookingUiViewModel = viewModel(),
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.select_departure_from_caption),
                style = MaterialTheme.typography.labelMedium
            )
            FromUserInput(
                onDestinationSelectionClicked = { departure->
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.DeparturePlaceSelected(departure))
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.DeparturePickupStationSelected(departure))
                    onFromClick(departure)
                },
                destinationSelected = bookingUiState.departurePlace
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Divider(
                    modifier = Modifier.fillMaxWidth(0.91f),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_interchange),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )

            }

            Text(
                text = stringResource(id = R.string.select_destination_to_caption),
                style = MaterialTheme.typography.labelMedium
            )
            ToUserInput(
                onDestinationSelectionClicked = { destination->
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.DestinationPlaceSelected(destination))
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.DestinationDropOffStationSelected(destination))
                    onToClick(destination)
                },
                destinationSelected = bookingUiState.destinationPlace
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