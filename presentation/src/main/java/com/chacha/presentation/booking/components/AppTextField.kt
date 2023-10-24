package com.chacha.presentation.booking.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    hint: String = "",
    hint2: String = "",
    maxLength: Int = 40,
    maxLines: Int = 1,
    enabled: Boolean = false,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    colors: TextFieldColors = TextFieldDefaults.colors(
        disabledTextColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        cursorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
    ),
    title: String = "",
    readOnly: Boolean = true,
) {

    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    if (interactionSource.collectIsPressedAsState().value)
        expanded = !expanded

    val textFieldColors = TextFieldDefaults.colors(
        disabledTextColor = MaterialTheme.colorScheme.onSurface,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        cursorColor = Color.Transparent,
        errorCursorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = Color.Transparent,
        unfocusedLeadingIconColor = Color.Transparent,
        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorLeadingIconColor = Color.Transparent,
        focusedTrailingIconColor = Color.Transparent,
        unfocusedTrailingIconColor = Color.Transparent,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorTrailingIconColor = Color.Transparent,
        focusedLabelColor = Color.Transparent,
        unfocusedLabelColor = Color.Transparent,
        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        //For Icons
        errorLabelColor = Color.Transparent,
        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(interactionSource, null, onClickLabel = value) {
                onValueChange(value)
            }
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(interactionSource, null, onClickLabel = title) {
                    onValueChange(title)
                },
            style = MaterialTheme.typography.labelSmall
        )

        BoxWithConstraints(
            modifier = Modifier.clipToBounds(),
            contentAlignment = Alignment.Center
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(interactionSource, null, onClickLabel = value) {
                        onValueChange(value)
                    }
                    .requiredWidth(maxWidth + 16.dp)
                    .offset(x = (-8).dp, y = (-8).dp),
                value = value,
                onValueChange = {
                    onValueChange(it)
                },
                enabled = enabled,
                maxLines = maxLines,
                placeholder = {
                        Text(
                            text = hint,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(interactionSource, null, onClickLabel = hint) {
                                    onValueChange(hint)
                                },
                            color = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                            style = MaterialTheme.typography.labelMedium
                        )

                },
                colors = textFieldColors,
                readOnly = readOnly,
            )
        }


    }
}
