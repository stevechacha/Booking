package com.chacha.presentation.bus_seat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun BusSeatArrangement() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(14) { rowIndex ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(50.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${rowIndex * 4 + 1}",
                        color = Color.White
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(50.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${rowIndex * 4 + 2}",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(50.dp))
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(50.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${rowIndex * 4 + 3}",
                        color = Color.White
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(50.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${rowIndex * 4 + 4}",
                        color = Color.White
                    )
                }
            }
        }
    }
}
