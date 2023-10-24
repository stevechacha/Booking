package com.chacha.presentation.booking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chacha.presentation.base.BottomSheets
import com.chacha.presentation.booking.tabs.multicity.MultiCityBookingScreen
import com.chacha.presentation.booking.tabs.one_way.OneWayBookingScreen
import com.chacha.presentation.booking.tabs.returns_booking.ReturnBookingScreen
import com.chacha.presentation.common.components.AppToolbar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingScreen(
    navController: NavController,
    bookingUiViewModel: BookingUiViewModel = viewModel(),

    ) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                AppToolbar(
                    title = "Booking",
                    showBackArrow = true
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                BookingPager(navController, bookingUiViewModel)
            }
        }

        BottomSheets()
    }


}


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingPager(
    navController: NavController,
    bookingUiViewModel: BookingUiViewModel = viewModel(),

    ) {

    val  bookingUiState by bookingUiViewModel.uiState.collectAsState()


    val pagerState = rememberPagerState(initialPage = 0)
    val pages = BookingPages.values().asList()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collectLatest { settledPage ->
            bookingUiViewModel.handleUiEvent(BookingUiEvent.OnEventPageChange(settledPage))
        }
    }

    val animateContentSizeModifier = Modifier.animateContentSize()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = {
            val modifier = Modifier.tabIndicatorOffset(it[pagerState.currentPage])
            TabRowDefaults.Indicator(
                modifier,
                height = 3.dp,
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
        modifier = animateContentSizeModifier, // Apply animateContentSize modifier
    ) {
        pages.forEachIndexed { index, item ->
            val isSelected = pagerState.currentPage == index
            Tab(
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
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold
                    )
                },
                modifier = animateContentSizeModifier, // Apply animateContentSize modifier
            )
        }
    }

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState,
        count = pages.size,
        key = { it },
        userScrollEnabled = true
    ) { page ->
        AnimatedContent(
            targetState = page, label = "",
        ) { pages ->
            when (pages) {
                0 -> ReturnBookingScreen(navController)
                1 -> OneWayBookingScreen(navController)
                2 -> MultiCityBookingScreen(navController)
            }
        }

    }
}



