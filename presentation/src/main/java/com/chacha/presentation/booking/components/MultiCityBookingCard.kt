package com.chacha.presentation.booking.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chacha.presentation.common.components.RideCard
import com.chacha.presentation.common.theme.Border

@Composable

fun MultiCityBookingCard(
    fromContent: @Composable ColumnScope.() -> Unit,
    toContent: @Composable ColumnScope.() -> Unit,
    dateContent: @Composable ColumnScope.() -> Unit,
    flightNo: Int = 1

    ) {
    RideCard{
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Column {
                Text(
                    text = "flight $flightNo",
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(0.72f)
            ) {
               fromContent()
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
                toContent()
            }
            Column(modifier = Modifier.weight(1f)) {
                dateContent()
            }

        }

    }

}