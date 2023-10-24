package com.chacha.presentation.activity

import android.content.Context
import android.graphics.Color.TRANSPARENT
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.chacha.presentation.common.theme.BookingTheme
import com.chacha.presentation.common.theme.ThemeMode
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Locale

val Context.dataStore by preferencesDataStore("settings")
var Context.appTheme by mutableStateOf(ThemeMode.SYSTEM)
var Context.appLocale: Locale? by mutableStateOf(null)
var Context.systemLocale: Locale? by mutableStateOf(null)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val isDone: MutableState<Boolean> = mutableStateOf(false)
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {

        val context = this.applicationContext
        lifecycleScope.launch { context.dataStore.data.first() }
        super.onCreate(savedInstanceState)
        setContent {
            val localContext = LocalContext.current
            LaunchedEffect(Unit) {
               /* syncTheme(localContext)
                syncOverrideLocale(localContext)*/
                isDone.value = true
            }
            if (isDone.value) {
                BookingTheme {
                    RootScreen()
                }
            }
        }
    }
}
