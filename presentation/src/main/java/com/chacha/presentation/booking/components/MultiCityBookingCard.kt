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

    ) {
    RideCard{
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "flight 1",
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(start = 10.dp,),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
               fromContent()
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(0.8f),
                    color = MaterialTheme.colorScheme.onBackground
                )
                toContent()
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                dateContent()
            }


        }

    }

}