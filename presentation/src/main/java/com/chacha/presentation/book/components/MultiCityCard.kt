package com.chacha.presentation.book.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chacha.presentation.common.theme.BookingTheme
import com.chacha.presentation.common.theme.Border
import java.time.LocalDate
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MultiCityCard(
    flightNumber: String,
    @StringRes departureHint: Int,
    @StringRes destinationHint: Int,
    flightOneDeparture: String?,
    flightOneDestination: String?,
    showCurrentDate: String,
    flightOneDate:String?,
    onDateFlightOneClick: () -> Unit,
    onFlightOneDepartureClick: ()->Unit,
    onFlightOneDestinationClick: ()->Unit,

) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        border = BorderStroke(1.dp, Border),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(3.dp),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = flightNumber,
                fontSize = 16.sp,

            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(start = 10.dp, end = 5.dp)
                ) {
                if (flightOneDeparture==null || flightOneDeparture.isEmpty()){
                    Text(
                        text = stringResource(id = departureHint),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .clickable(onClick = onFlightOneDepartureClick),
                        fontSize = 24.sp
                    )

                }else{
                    Text(
                     text = flightOneDeparture,
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(top = 8.dp)
                         .clickable(onClick = onFlightOneDepartureClick),
                     fontSize = 24.sp
                 )

                }
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                if (flightOneDestination==null || flightOneDestination.isEmpty()){
                    Text(
                        text = stringResource(id = destinationHint),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .clickable(onClick = onFlightOneDestinationClick),
                        fontSize = 24.sp
                    )

                }else{
                    Text(
                        text = flightOneDestination,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .clickable(onClick = onFlightOneDestinationClick),
                        fontSize = 24.sp
                    )

                }
            }

            if (flightOneDate==null || flightOneDate.isEmpty()){
                val currentDate = LocalDate.now()
               val  showCurrentDatee = currentDate.toString()

                Text(
                    text = showCurrentDate,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable(onClick = onDateFlightOneClick),
                    fontSize = 15.sp
                )

        } else{
                Text(
                    text = flightOneDate,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable(onClick = onDateFlightOneClick),
                    fontSize = 15.sp
                )

            }
        }

    }

}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun MultiCityPreview() {
    BookingTheme {
        MultiCityCard(
            flightNumber = "Flight 1",
            departureHint = 0,
            destinationHint = 0,
            flightOneDeparture = "London",
            flightOneDestination = "Paris",
            flightOneDate = "12/12/2021",
            onFlightOneDepartureClick = {},
            onFlightOneDestinationClick = {},
            onDateFlightOneClick = {},
            showCurrentDate = "12/12/2021"
        )
    }
    
}