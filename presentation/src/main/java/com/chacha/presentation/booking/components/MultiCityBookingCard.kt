package com.chacha.presentation.booking.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chacha.presentation.common.theme.Border

@Composable
@Preview
fun MultiCityBookingCard() {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        border = BorderStroke(1.dp, Border),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "flight 1",
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(start = 10.dp,),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AppTextField(
                    title = "Select Flight One",
                    hint = "Select Flight From",
                    onValueChange = {}
                )
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(0.85f),
                    color = MaterialTheme.colorScheme.onBackground
                )
                AppTextField(
                    title = "Select Flight two",
                    hint = "Select Flight To",
                    onValueChange = {}
                )


            }
            Spacer(modifier = Modifier.weight(1f))
            AppTextField(
                title = "Select Date",
                onValueChange = {},
                modifier = Modifier.align(Alignment.CenterVertically)

            )
        }

    }

}