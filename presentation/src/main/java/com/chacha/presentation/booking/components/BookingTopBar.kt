package com.chacha.presentation.booking.components

import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun BookingTopBar(
    title: String,
    onClickBack:()->Unit
) {
    TopAppBar(
        title = { Text(text = title)},
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icons.Outlined.ArrowBack
            }
        }
    )

}