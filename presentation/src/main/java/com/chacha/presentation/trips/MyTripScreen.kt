package com.chacha.presentation.trips

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.material3.TabRow
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chacha.presentation.booking.BookingEvent
import com.chacha.presentation.common.components.AppToolbar
import com.chacha.presentation.trips.tabs.CancelledTripScreen
import com.chacha.presentation.trips.tabs.CompletedTripScreen
import com.chacha.presentation.trips.tabs.UpcomingTripScreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyTripScreen() {
    val viewModel: MyTripsViewModel = viewModel()
    Scaffold(
        topBar = {
            AppToolbar(
                title = "My Trips",
                showBackArrow = true
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            MyTripsPager(viewModel)
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyTripsPager(viewModel: MyTripsViewModel) {
    val pagerState = rememberPagerState()
    val pages = MyTripPages.values().asList()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collectLatest { settledPage ->
            viewModel.onTriggerEvent(BookingEvent.OnEventPageChange(settledPage))
        }
    }

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = {
            val modifier = Modifier.tabIndicatorOffset(it[pagerState.currentPage])
            TabRowDefaults.Indicator(
                modifier,
                height = 1.dp,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        divider = {
            Divider(
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) {
        pages.forEachIndexed { index, item ->
            val isSelected = pagerState.currentPage == index
            androidx.compose.material3.Tab(
                selected = isSelected,
                onClick = {
                    coroutineScope.launch { pagerState.animateScrollToPage(index) }
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                text = {
                    Text(
                        text = stringResource(id = item.title),
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                    )
                }
            )
        }
    }

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState,
        count = pages.size,
        key = { it }
    ) { page ->

        when (page) {
            0 -> UpcomingTripScreen()
            1 -> CompletedTripScreen()
            2 -> CancelledTripScreen()
        }
    }

}