package com.chacha.presentation.trips.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chacha.presentation.trips.component.MyTripCardItem

@Composable
@Preview
fun CompletedTripScreen() {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp)
        ){
            items(10){
                MyTripCardItem()
            }
        }

    
}

@Composable
@Preview
fun PreviewCompletedTripScreen() {
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(10){
                MyTripCardItem()
            }
        }
    }
}