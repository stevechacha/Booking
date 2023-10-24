package com.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.navigation.compose.composable
import com.chacha.presentation.common.navigation.GraphDestinations.DESTINATION_ROUTE
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.destinationNavGraph(navController: NavController) {
    composable(route = DESTINATION_ROUTE){
        DestinationScreen(navController)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun  DestinationScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
    }

}
