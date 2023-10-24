package com.chacha.presentation.trips.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chacha.presentation.R

@Composable
@Preview
fun MyTripCardItem() {
    OutlinedCard (
        modifier = Modifier.fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(0.dp)
    ){
        Icon(
            painter = painterResource(id = R.drawable.airline_seat_recline),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
        ) {

            Text(text = "Trip Name")

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text = "Trip Date"
                )
                Text(
                    text = "Trip Time",
                    modifier = Modifier.padding(start = 12.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Ticket No",)
            }

        }
    }
}