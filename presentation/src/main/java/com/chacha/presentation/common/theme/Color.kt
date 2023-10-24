package com.chacha.presentation.common.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xffFF4500)
val PrimaryLightColor = Color(0xffffe3d5)

val SecondaryColor = Color(0xff6167FF)
val SecondaryLightColor = Color(0xffBEC1FF)

val PrimaryTextColor = Color(0xffffffff)
val SecondaryTextColor = Color(0xff000000)

val SurfaceDark = Color(0xFF3A3A3A)
val SurfaceLight = Color(0xFFFFFFFF)

val BackgroundLightColor = Color(0xffF1F0F5)
val BackgroundDarkColor = Color(0xff121212)

val ErrorColor = Color(0xFFFF8989)
val OnErrorColor = Color(0xFF000000)

val mainBackground = Color(0xFF1E2329)
val StaxCardBlue = Color(0xFF4464F6)
val TextColorDark = Color(0xFF1E2329)
val NavGrey = Color(0xFF3F444A)

val GrayMainColor=Color(0XFFF1F1F3)
val Gray = Color(0XFF8A8B8F) //sub text
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val SubTextColor= Color(0XFF8A8B8F)
val DarkBlue= Color(0XFF141721)
val DarkPink=Color(0XFF2C121B)
val WhiteAlphaAA =Color(0xAAFFFFFF)
val WhiteAlpha95 =Color(0x95FFFFFF)
val WhiteAlpha60 =Color(0x60FFFFFF)
val Gray20=Color(0XFF202020)
val GrayLight=Color(0xFF413F3F)
val SeparatorColor = Color(0X458A8B8F)
val OverlayWhiteColor =Color(0x6Affffff)
val WhiteLightDimBg = Color(0xFFF4f4f4)

val TealColor=Color(0xFF196D77)
val LightGreenColor=Color(0xFF676962)

val Border = Color(0xFF535457)




val colorBackground
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.colorScheme.surface

val colorOnBackground
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.colorScheme.onSurface

val colorButton
    @Composable
    @ReadOnlyComposable
    get() =
            MaterialTheme.colorScheme.secondaryContainer

val colorOnButton
    @Composable
    @ReadOnlyComposable
    get() =
        MaterialTheme.colorScheme.onSurfaceVariant

val colorEditor
    @Composable
    @ReadOnlyComposable
    get() =
        MaterialTheme.colorScheme.surface

val colorOnEditor
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.colorScheme.onSurface