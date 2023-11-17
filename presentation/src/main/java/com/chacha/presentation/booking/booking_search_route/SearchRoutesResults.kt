package com.chacha.presentation.booking.booking_search_route

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.booking_calender.dateFormatterr
import com.chacha.presentation.booking.model.BusBooking
import com.chacha.presentation.common.components.AppToolbar
import java.time.LocalDate
import java.time.format.DateTimeParseException

@SuppressLint("NewApi")
@Composable
fun SearchRoutesResultsContent(
    searchResults: List<BusBooking>,
    onClick: (BusBooking) -> Unit,
    navController: NavController,
) {
    val bookingUiViewModel: BookingUiViewModel = viewModel()
    val bookingUiState by bookingUiViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppToolbar(
                showBackArrow = true,
                title = "${bookingUiState.departurePlace.uppercase()}   To   ${bookingUiState.destinationPlace.uppercase()}"
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
//            Example5Page()
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    try {
                        val currentDate = LocalDate.now()
                        val newDate = bookingUiState.departureDate.minusDays(1)

                        // Check if the new date is not before the current date
                        if (!newDate.isBefore(currentDate)) {
                            bookingUiViewModel.handleUiEvent(
                                BookingUiEvent.DepartureDateSelected(newDate)
                            )
                        }
                    } catch (e: DateTimeParseException) {
                        e.printStackTrace()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = null
                    )
                }
                Text(
                    text = dateFormatterr.format(bookingUiState.departureDate).toString(),
                )
                IconButton(onClick = {
                    try {
                        val newDate = bookingUiState.departureDate.plusDays(1)
                        bookingUiViewModel.handleUiEvent(
                            BookingUiEvent.DepartureDateSelected(newDate)
                        )
                    } catch (e: DateTimeParseException) {
                        e.printStackTrace()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = null
                    )
                }
            }
            if (searchResults.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Bus Available",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(searchResults) { flight ->
                        FlightItem(
                            busBooking = flight,
                            onClick = { searchResults ->
                                val destinationCity = searchResults.destinationCity.city
                                val destinationCode = searchResults.destinationCity.code
                                val departureCity = searchResults.departureCity.city
                                val departureCode = searchResults.departureCity.code
                                onClick(flight)
                                navController.navigate("bus/${destinationCity}/${destinationCode}/${departureCity}/${departureCode}")
                            }
                        )
                    }
                }
            }

        }
    }


}

data class SearchRoutesResults(
    val id: Int = 0,
    val text: String = ""
)