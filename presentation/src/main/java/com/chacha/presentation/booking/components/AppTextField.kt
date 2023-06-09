package com.chacha.presentation.booking.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    hint: String = "",
    maxLength: Int = 50,
    maxLines: Int = 1,
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
        cursorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        disabledTextColor = Color.Transparent,
    ),
    title: String = "",
    onClick: () -> Unit = {}
) {

    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    if (interactionSource.collectIsPressedAsState().value)
        expanded = !expanded

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        BasicTextField(
            value = value,
            onValueChange ={
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            enabled = enabled,
            singleLine = singleLine,
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            decorationBox = { innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    value = value,
                    visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                    innerTextField = innerTextField,
                    singleLine = singleLine,
                    enabled = enabled,
                    colors = colors,
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(start=0.dp,end=0.dp, bottom = 12.dp),
                    placeholder = {
                        Text(
                            text = hint,
                            color = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                            textAlign = TextAlign.Start,
                            modifier = modifier.clickable { onClick() },
                            fontSize = 14.sp
                        )
                    }
                )
            },
            modifier = modifier.fillMaxWidth(),
            readOnly = true
        )
    }
}
