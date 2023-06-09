package com.chacha.presentation.booking.departure_destination

import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.chacha.presentation.booking.BookingViewModel
import com.chacha.presentation.common.navigation.GraphDestinations.DEPARTURE_DESTINATION
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalMaterialApi::class,
    ExperimentalCoroutinesApi::class
)
fun NavGraphBuilder.departureDestinationNavGraph(
    navController: NavController,
    sheetState: BottomSheetState,
) {
    bottomSheet(route = DEPARTURE_DESTINATION) {
        val departureDestinationViewModel: DepartureDestinationViewModel = viewModel()
        val departureDestinationState by departureDestinationViewModel.state.collectAsState()
        val coroutineScope = rememberCoroutineScope()
        DepartureDestinationsScreen(
            onDepartureDestinationClick = { departureList->
                departureDestinationViewModel.onEvent(DepartureDestinationEvent.GetDepartureDestinationList)
                coroutineScope.launch {
                    sheetState.collapse()
                }

            },
            navController = navController
        )
    }

}