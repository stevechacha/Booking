package com.chacha.presentation.booking.booking_component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chacha.presentation.R
import com.chacha.presentation.common.theme.BookingTheme
import com.chacha.presentation.common.theme.HintGray
import com.chacha.presentation.common.theme.captionTextStyle

@Composable
fun SimpleUserInput(
    text: String? = null,
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null
) {
    CraneUserInput(
        caption = if (text == null) caption else null,
        text = text ?: "",
        vectorImageId = vectorImageId
    )
}

@Composable
fun CraneUserInput(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null,
    tint: Color = LocalContentColor.current
) {
    CraneBaseUserInput(
        modifier = modifier,
        onClick = onClick,
        caption = caption,
        vectorImageId = vectorImageId,
        tintIcon = { text.isNotEmpty() },
        tint = tint
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun CraneEditableUserInput(
    hint: String,
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null,
    onInputChanged: (String) -> Unit
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    var textFieldState by rememberSaveable(stateSaver = TextFieldValue.Companion.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }
    CraneBaseUserInput(
        caption = caption,
        tintIcon = {
            textFieldState.text.isNotEmpty()
        },
        showCaption = {
            textFieldState.text.isNotEmpty()
        },
        vectorImageId = vectorImageId
    ) {
        BasicTextField(
            value = textFieldState,
            onValueChange = {
                textFieldState = it
                onInputChanged(textFieldState.text)
            },
            textStyle = MaterialTheme.typography.labelMedium.copy(color = LocalContentColor.current),
            cursorBrush = SolidColor(LocalContentColor.current),
            decorationBox = { innerTextField ->
                if (hint.isNotEmpty() && textFieldState.text.isEmpty()) {
                    Text(
                        text = hint,
                        style = captionTextStyle.copy(color = LocalContentColor.current),
                    )
                }
                innerTextField()
            },
            readOnly = true,
            interactionSource = interactionSource,
            enabled = false
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CraneBaseUserInput(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null,
    showCaption: () -> Boolean = { true },
    tintIcon: () -> Boolean,
    tint: Color = LocalContentColor.current,
    content: @Composable () -> Unit
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Surface(
        modifier = modifier,
        onClick = onClick,
        color = Color.Transparent,
        interactionSource = interactionSource
    ) {

        Row(Modifier.padding(vertical = 1.dp)) {
            if (vectorImageId != null) {
                Icon(
                    modifier = Modifier.size(24.dp, 24.dp),
                    painter = painterResource(id = vectorImageId),
                    tint = if (tintIcon()) tint else Color(0x80FFFFFF),
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
            }
            if (caption != null && showCaption()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = caption,
                    color = HintGray
                )
                Spacer(Modifier.width(8.dp))
            }
            Row(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                content()
            }
        }
    }

}

@Preview
@Composable
fun PreviewInput() {
    BookingTheme {
        Surface {
            CraneBaseUserInput(
                tintIcon = { true },
                vectorImageId = R.drawable.ic_menu,
                caption = "Caption",
                showCaption = { true }
            ) {
                Text(text = "text", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}