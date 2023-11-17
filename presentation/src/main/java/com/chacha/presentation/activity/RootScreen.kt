package com.chacha.presentation.activity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.chacha.presentation.base.AppViewModel
import com.chacha.presentation.bottomnav.BottomNavigationBar
import com.chacha.presentation.common.navigation.RootNavGraph
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@OptIn(ExperimentalMaterialNavigationApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun RootScreen(
) {
    val navController = rememberNavController()

    val isNightModeM = remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding()
                .padding(padding)
        ) {
            RootNavGraph(navController = navController)
        }
    }

}



