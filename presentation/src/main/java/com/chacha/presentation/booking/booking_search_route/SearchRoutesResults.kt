package com.chacha.presentation.booking.booking_search_route

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chacha.presentation.booking.BookingUiViewModel
import timber.log.Timber

@Composable
fun SearchResultsScreen(
    bookingUiViewModel: BookingUiViewModel = viewModel(),
) {
    val uiState by bookingUiViewModel.uiState.collectAsState()

    // Check if search results are available in the ViewModel

    val searchResults = uiState.searchResults

    /*val searchResults = bookingUiViewModel.searchFlights(
        uiState.departurePlace, uiState.destinationPlace, uiState.departureDate)*/


    Timber.tag("SearchResultsScreen").d("Search Results: %s", searchResults)


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        /*items(searchResults) { flight ->
            Log.d("SearchResultsContent", "Flight Item: $flight")
            FlightItem(
                flight = flight,
                onClick = {}
            )
        }*/

        item {
            Text(text = "Selected DepartureDate: ${uiState.departureDate}")
            Text(text = "Selected Destination Place: ${uiState.destinationPlace}")
            Text(text = "Selected Departre place: ${uiState.departurePlace}")
            Text(text = "Search place: ${searchResults.toString()}")
        }
    }


}

data class SearchRoutesResults(
    val id: Int = 0,
    val text: String = ""
)