package com.chacha.presentation.booking.departure_destination

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingEvent
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingViewModel
import com.chacha.presentation.common.components.AppTopBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun DepartureDestinationItemScreen(
    departureDestinationItem: DepartureDestinationItem,
    OnDepartureDestinationClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { OnDepartureDestinationClick() }
        ) {
            Text(text = departureDestinationItem.departureDestination)
            Text(text = departureDestinationItem.departurePickingPoint)
        }
    }

}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun DepartureDestinationsScreen(
    onDepartureDestinationClick: (DepartureDestinationItem) -> Unit,
    navController: NavController,
    departureDestinationViewModel: DepartureDestinationViewModel = viewModel(),
) {
    val departureDestinationState by departureDestinationViewModel.state.collectAsState()
    val departureList = departureDestinationState.departureDestinationList

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Search Departure Destination" ,
                initialValue = "",
                onSearchParamChange = { searchParams ->
                    departureDestinationViewModel.onEvent(
                        DepartureDestinationEvent.SearchDepartureDestination(searchParams)
                    )
                },
            )
        }

    ){ paddingValues ->
        if (departureDestinationState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            if (departureDestinationState.departureDestinationList.isEmpty()) {
                NoMatchFound()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues = paddingValues)
                ) {
                    item(10) {
                        DepartureDestinationsScreen(
                            onDepartureDestinationClick = {
                                departureDestinationViewModel.onEvent(DepartureDestinationEvent.GetDepartureDestinationList)
                                navController.previousBackStackEntry?.savedStateHandle?.set("selectDepartureDestination", departureList)
                                navController.popBackStack()
                                onDepartureDestinationClick(it)
                            },
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NoMatchFound() {
    TODO("Not yet implemented")
}

data class DepartureDestinationItem(
    val departureDestination: String,
    val departurePickingPoint: String,
)

