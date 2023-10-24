package com.chacha.presentation.base

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.semantics.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chacha.presentation.common.navigation.GraphDestinations.CURRENCY_EDITOR
import com.chacha.presentation.common.navigation.GraphDestinations.FINISH_DATE_SELECTOR_SHEET
import com.chacha.presentation.common.navigation.GraphDestinations.SETTINGS_CHANGE_LOCALE_SHEET
import com.chacha.presentation.common.navigation.GraphDestinations.SETTINGS_CHANGE_THEME_SHEET
import com.chacha.presentation.common.navigation.GraphDestinations.SETTINGS_SHEET
import com.chacha.presentation.settings.Settings
import com.chacha.presentation.settings.ThemeSwitcherDialog
import kotlinx.coroutines.launch
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheets(
    appViewModel: AppViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()

    BottomSheetWrapper(
        name = CURRENCY_EDITOR,
    ) { state ->
        CurrencyEditor(
            onClose = {
                coroutineScope.launch {
                    state.hide()
                }
            }
        )
    }

    BottomSheetWrapper(
        name = SETTINGS_CHANGE_THEME_SHEET,
    ) { state ->
        ThemeSwitcherDialog(
            onClose = {
                coroutineScope.launch { state.hide() }
            }
        )
    }





    BottomSheetWrapper(
        name = FINISH_DATE_SELECTOR_SHEET,
    ) { state ->
        FinishDateSelector(
            selectDate = state.args["initialDate"] as Date?,
            onBackPressed = {
                coroutineScope.launch {
                    state.hide()
                }
            },
            onApply = {
                coroutineScope.launch {
                    state.hide(mapOf("finishDate" to it))
                }
            },
        )
    }


}

@Composable
fun CurrencyEditor(onClose: () -> Unit) {

}


