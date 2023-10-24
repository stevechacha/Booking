package com.chacha.presentation.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RenderAdaptivePane(
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit,
) {
    Column {
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = contentAlignment,
        ) {
            content()
        }
    }

}