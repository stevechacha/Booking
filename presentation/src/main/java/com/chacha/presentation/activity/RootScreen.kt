package com.chacha.presentation.activity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.chacha.presentation.bottomnav.BottomNavigationBar
import com.chacha.presentation.common.navigation.RootNavGraph
import com.chacha.presentation.common.theme.BookingTheme
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@OptIn(ExperimentalMaterialNavigationApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun RootScreen() {
//    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController()

    BookingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = { BottomNavigationBar(navController) }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    RootNavGraph(navController = navController,)

                }
            }
        }
    }

}

@ExperimentalMaterialNavigationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberBottomSheetNavigator(
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    skipHalfExpanded: Boolean = true,
): BottomSheetNavigator {
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        animationSpec,
        skipHalfExpanded = true,
    )
    return remember(sheetState) {
        BottomSheetNavigator(sheetState = sheetState)
    }
}


