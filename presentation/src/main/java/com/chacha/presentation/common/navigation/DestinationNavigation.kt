package com.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.chacha.presentation.book.components.BookingBottomSheet
import com.chacha.presentation.common.navigation.GraphDestinations.DESTINATION_ROUTE
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.destinationNavGraph(navController: NavController,sheetState: BottomSheetState) {
    bottomSheet(route = DESTINATION_ROUTE){
        DestinationScreen(navController,sheetState)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun  DestinationScreen(navController: NavController, sheetState: BottomSheetState) {
    var fromDeparture by remember { mutableStateOf("") }
    var toDestination by remember { mutableStateOf("") }
    var showDepartureList by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var showDestinationList by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        BookingBottomSheet(
            title = "Select From",
            items = listOf("Location A", "Location B", "Location C"),
            selectedItem = fromDeparture,
            onItemSelected = { from ->
                fromDeparture = from
                showDepartureList = false
                navController.navigateUp()
                scope.launch {
                    sheetState.collapse()
                }

            },
            onDismiss = {
                scope.launch {
                    if (sheetState.isCollapsed){
                        sheetState.expand()
                    } else{
                        sheetState.collapse()

                    }
                }
            },
            onItemToSelected = { to->
                toDestination = to
                navController.navigateUp()
                scope.launch {
                    if (sheetState.isCollapsed){
                        sheetState.expand()
                    } else{
                        sheetState.collapse()

                    }
                }

            },
        )
    }

}
