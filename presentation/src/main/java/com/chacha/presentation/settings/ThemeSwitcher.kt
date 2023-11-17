package com.chacha.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chacha.presentation.activity.appTheme
import com.chacha.presentation.base.AppViewModel
import com.chacha.presentation.base.PathState
import com.chacha.presentation.common.theme.BookingTheme
import com.chacha.presentation.common.theme.ThemeMode
import com.chacha.presentation.common.theme.switchTheme
import com.chacha.presentation.util.combineColors
import kotlinx.coroutines.launch
import java.util.Locale
import com.chacha.presentation.R
import com.chacha.presentation.common.navigation.GraphDestinations.SETTINGS_CHANGE_THEME_SHEET


@Composable
fun ThemeSwitcher(appViewModel: AppViewModel = hiltViewModel()) {
    val context = LocalContext.current

    ButtonRow(
        icon = painterResource(R.drawable.ic_dark_mode),
        text = stringResource(R.string.theme_label),
        onClick = {
            appViewModel.openSheet(PathState(SETTINGS_CHANGE_THEME_SHEET))
        },
        endCaption = when (context.appTheme) {
            ThemeMode.LIGHT -> stringResource(R.string.theme_light)
            ThemeMode.NIGHT -> stringResource(R.string.theme_dark)
            ThemeMode.SYSTEM -> stringResource(R.string.theme_system)
        },
    )
}

@Composable
fun ThemeSwitcherDialog(onClose: () -> Unit) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val navigationBarHeight = androidx.compose.ui.unit.max(
        WindowInsets.systemBars.asPaddingValues().calculateBottomPadding(),
        16.dp,
    )

    fun handleSwitchTheme(mode: ThemeMode) {
        coroutineScope.launch {
            switchTheme(context, mode)
            onClose()
        }
    }

    Surface {
        Column(modifier = Modifier.fillMaxWidth().padding(bottom = navigationBarHeight)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.theme_label),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            CheckedRow(
                checked = context.appTheme == ThemeMode.LIGHT,
                onValueChange = { handleSwitchTheme(ThemeMode.LIGHT) },
                text = stringResource(R.string.theme_light),
            )
            CheckedRow(
                checked = context.appTheme == ThemeMode.NIGHT,
                onValueChange = { handleSwitchTheme(ThemeMode.NIGHT) },
                text = stringResource(R.string.theme_dark),
            )
            CheckedRow(
                checked = context.appTheme == ThemeMode.SYSTEM,
                onValueChange = { handleSwitchTheme(ThemeMode.SYSTEM) },
                text = stringResource(R.string.theme_system),
            )

        }
    }
}

@Composable
fun CheckedRow(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onValueChange: (isChecked: Boolean) -> Unit,
    text: String,
    description: String? = null,
    endContent: @Composable (() -> Unit)? = null,
    endCaption: String? = null,
) {
    TextRow(
        modifier = modifier
            .toggleable(
                value = checked,
                onValueChange = { onValueChange(!checked) },
                role = Role.Checkbox
            ),
        icon = if (checked) painterResource(R.drawable.ic_apply) else null,
        iconTint = MaterialTheme.colorScheme.primary,
        text = text,
        description = description,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        endContent = endContent,
        endCaption = endCaption,
    )
}

@Composable
fun ButtonRow(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    iconInset: Boolean = true,
    endIcon: Painter? = null,
    text: String,
    wrapMainText: Boolean = false,
    description: String? = null,
    denseDescriptionOffset: Boolean = true,
    onClick: () -> Unit,
    endContent: @Composable (() -> Unit)? = null,
    endCaption: String? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }

    TextRow(
        modifier
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple()
            ) { onClick.invoke() },
        icon = icon,
        iconInset = iconInset,
        endIcon = endIcon,
        wrapMainText = wrapMainText,
        text = text,
        description = description,
        denseDescriptionOffset = denseDescriptionOffset,
        endContent = endContent,
        endCaption = endCaption,
    )
}

@Composable
fun TextRow(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    iconTint: Color = contentColorFor(
        combineColors(
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.surfaceVariant,
            angle = 0.3F,
        )
    ),
    endIcon: Painter? = null,
    endContent: @Composable (() -> Unit)? = null,
    endCaption: String? = null,
    iconInset: Boolean = true,
    text: String,
    wrapMainText: Boolean = false,
    description: String? = null,
    denseDescriptionOffset: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    descriptionTextStyle: TextStyle = MaterialTheme.typography.bodyMedium
        .copy(color = LocalContentColor.current.copy(alpha = 0.6f))
) {
    Column(modifier) {
        Box(Modifier.padding(top = 16.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .heightIn(24.dp)
                    .padding(horizontal = 16.dp)
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    Modifier
                        .padding(
                            start = if (!iconInset && icon === null) 8.dp else (24 + 16).dp,
                            top = 0.dp,
                            bottom = if (description !== null) 0.dp else 16.dp,
                        )
                        .heightIn(24.dp)
                        .widthIn(min = 100.dp)
                        .weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = text,
                        style = textStyle,
                        softWrap = wrapMainText,
                        overflow = if (wrapMainText) TextOverflow.Visible else TextOverflow.Ellipsis,
                    )
                }

                if (endCaption !== null) {
                    Text(
                        modifier = Modifier.widthIn(max = 200.dp),
                        text = endCaption,
                        softWrap = false,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge,
                        color = LocalContentColor.current.copy(alpha = 0.6f),
                    )
                }

                if (endContent !== null || endIcon !== null) {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.TopEnd,
                    ) {
                        Row(
                            Modifier
                                .height(24.dp)
                                .padding(start = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            if (endContent !== null) {
                                endContent()
                                if (endIcon == null) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                            }
                            if (endIcon !== null) {
                                Spacer(modifier = Modifier.width(16.dp))
                                Icon(
                                    painter = endIcon,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }

            if (icon !== null) {
                Box(
                    Modifier
                        .height(24.dp)
                        .width(56.dp)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        painter = icon,
                        tint = iconTint,
                        contentDescription = null
                    )
                }
            }
        }
        if (description !== null) {
            Text(
                text = description,
                style = descriptionTextStyle,
                softWrap = true,
                modifier = Modifier
                    .padding(
                        start = if (!iconInset && icon === null) 24.dp else (24 + 16 * 2).dp,
                        top = if (denseDescriptionOffset) 0.dp else 8.dp,
                        end = 24.dp,
                        bottom = 16.dp,
                    )
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    BookingTheme {
        Surface {
            ThemeSwitcher()
        }
    }
}