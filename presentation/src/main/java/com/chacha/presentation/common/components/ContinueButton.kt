package com.chacha.presentation.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.reflect.jvm.internal.impl.types.checker.TypeRefinementSupport.Enabled

@Composable
fun ContinueButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit),
    enabled:Boolean = false
) {
    AnimatedVisibility(
        visible = enabled,
        enter = fadeIn() /* You can customize enter and exit animations */,
        exit = fadeOut()

    ){
        Card(
            modifier = Modifier.fillMaxWidth()
                .padding( if (enabled)16.dp else 0.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {

            Button(
                onClick = onClick,
                modifier = modifier.fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                ),
                enabled = enabled
            ) {
                Text(
                    modifier = modifier.padding(top = 5.dp, bottom = 5.dp),
                    text = text,

                    )
            }
        }
    }

}