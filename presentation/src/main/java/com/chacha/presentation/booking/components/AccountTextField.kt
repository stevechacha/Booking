package com.chacha.presentation.booking.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AccountTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange:(String)->Unit,
    title: String = "",
    accountNumber: String = "",
    hint: String = "",
    maxLines: Int = 1,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    textFieldColors: TextFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
    focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,

    )
) {

    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    if (interactionSource.collectIsPressedAsState().value)
        expanded = !expanded

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            modifier = modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .56f),
            style = MaterialTheme.typography.labelSmall

        )
            BoxWithConstraints(
                modifier = modifier.clipToBounds(),
                contentAlignment = Alignment.Center
            ) {
                TextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .requiredWidth(maxWidth + 16.dp)
                        .offset(x = (-8).dp, y = (-8).dp),
                    value = value,
                    onValueChange = {
                        onValueChange(it)
                    },
                    maxLines = maxLines,
                    placeholder = {
                        Text(
                            text = hint,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .56f),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    colors = textFieldColors,
                    readOnly = readOnly,
                    enabled = enabled

                )
            }


    }

}

@Composable
@Preview(showBackground = true)
fun AccountNumberTextFieldPreview() {
    AccountTextField(
        value = "",
        onValueChange = {},
        title = "From",
        accountNumber = "12345678",
        hint = "Available balance is 52.80"
    )
}