package com.chacha.presentation.modal_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WeBookingModalSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val localDensity = LocalDensity.current
    var edgeToEdgeEnabled by remember { mutableStateOf(false) }
    val windowInsets = if (edgeToEdgeEnabled)
        WindowInsets(0,0,0,0) else BottomSheetDefaults.windowInsets

    BoxWithConstraints(
        modifier = modifier
    ) {
        Surface {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    onDismissRequest()
                },
                modifier = modifier.fillMaxHeight(0.9f),
                windowInsets = windowInsets,
                dragHandle = {
                    Spacer(modifier = Modifier.height(0.dp).width(0.dp)
                    .background(MaterialTheme.colorScheme.background))
                },
                containerColor = MaterialTheme.colorScheme.background

            ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                     modifier = Modifier
                         .safeGesturesPadding()
                         .fillMaxWidth(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        content()
                    }
                }


            }
        }

    }

}


