package com.chacha.presentation.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.chacha.presentation.bottomnav.BottomNavigationBar
import com.chacha.presentation.common.navigation.RootNavGraph
import com.chacha.presentation.common.navigation.HomeNavGraph
import com.chacha.presentation.common.theme.BookingTheme
import com.chacha.presentation.date_picker.DatePickerScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    DatePickerScreen()
//                    RootNavGraph(navController = navController)

                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { if (bottomBarState.value) BottomNavigationBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            HomeNavGraph(
                navController = navController,
                showBottomBar = { bottomBarState.value = it },
            )

        }
    }
}