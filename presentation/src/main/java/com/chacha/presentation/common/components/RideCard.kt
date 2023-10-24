package com.chacha.presentation.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chacha.presentation.common.theme.Border
import com.chacha.presentation.common.theme.mainBackground

@Composable
fun RideCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp,vertical = 12.dp)

    ) {
        Column(
            content = content,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}