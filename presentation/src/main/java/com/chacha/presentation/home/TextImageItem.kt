package com.chacha.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chacha.presentation.R
import com.chacha.presentation.common.theme.BookingTheme

@Composable
fun TextImageItem(
    @DrawableRes drawable: Int,
    @StringRes stringRes: Int,
    onItemClick : () -> Unit
) {
    val sizeIcon = dimensionResource(id = R.dimen.margin_24)
    Column(
        modifier = Modifier
            .clickable(onClick = onItemClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
                .background(MaterialTheme.colorScheme.background)
            ,
            contentAlignment = Alignment.Center

        ){
            Image(
                painter = painterResource(id = drawable),
                contentDescription = "",
                modifier = Modifier
                    .size(sizeIcon),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )

        }

        Text(
            text = stringResource(id = stringRes),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,

        )

    }
    
}

@Composable
@Preview
fun TextImageItemPreview() {
    BookingTheme {
        TextImageItem(
            drawable = R.drawable.profile_account,
            stringRes = R.string.submit
        ) {

        }

    }
}