package com.chacha.presentation.booking.tabs.returns_booking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chacha.presentation.booking.components.BookingCard
import com.chacha.presentation.booking.components.DateBookingCard
import com.chacha.presentation.booking.components.ReturnDateBookingCard
import com.chacha.presentation.booking.tabs.one_way.BottomSheetType
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(
    ExperimentalMaterialApi::class, ExperimentalPagerApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun ReturnBookingScreen(navController: NavController, pagerState: PagerState) {
    val oneWayBookingViewModel: OneWayBookingViewModel = viewModel()
    val oneWayState by oneWayBookingViewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookingCard(
            oneWayBookingState = oneWayState,
            onFromClick = {},
            onToClick = {},
            navController = navController
        )

        ReturnDateBookingCard(
            oneWayBookingState = oneWayState,
            onReturnDateChanged = {},
            onVehicleChanged = {},
            onPassengerNumberChanged = {},
            onDepartureDateChanged = {}
        )

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview
fun ReturnBookingPreview() {
    ReturnBookingScreen(
        navController = rememberNavController(),
        pagerState = rememberPagerState(),
    )
}

@Composable
fun SheetLayout(
    bottomSheetType: BottomSheetType,
    closeSheet: () -> Unit
) {

    when (bottomSheetType) {
        BottomSheetType.TYPE1 -> Screen1(closeSheet)
        BottomSheetType.TYPE2 -> Screen2(closeSheet)
        BottomSheetType.TYPE3 -> Screen3(closeSheet)
    }

}

@Composable
fun Screen1(closeSheet: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Bottom sheet type 1")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { closeSheet() }) {
                Text(text = "Close")
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }

}

@Composable
fun Screen2(closeSheet: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Bottom sheet type 2")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { closeSheet() }) {
                Text(text = "Close")
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun Screen3(closeSheet: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            LazyColumn {
                items(200) {
                    Text(text = "Item $it", modifier = Modifier.height(50.dp))
                }
            }
            Text(text = "Bottom sheet type 2")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { closeSheet() }) {
                Text(text = "Close")
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    val onClickSource = remember { MutableInteractionSource() }


    TextField(
        value = value,
        onValueChange = onValueChange,
        interactionSource = onClickSource,
        enabled = enabled,
        readOnly = onClick != null,
        modifier = modifier
            // add clickable to work with talkback/accessibility
            .clickable(enabled = enabled) { onClick?.invoke() },
    )
}




