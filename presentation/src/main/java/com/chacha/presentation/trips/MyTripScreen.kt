package com.chacha.presentation.trips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.TabRowDefaults.Divider
import com.chacha.presentation.book.MultiCityBook
import com.chacha.presentation.book.OneWayBook
import com.chacha.presentation.book.ReturnsBook
import com.chacha.presentation.common.components.AppToolbar
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyTripScreen() {
    val tabs = listOf(MyTripTabItem.Upcoming, MyTripTabItem.Completed, MyTripTabItem.Cancelled)
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = {
            AppToolbar(
                title = "My Trips",
                showBackArrow = true
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            MyTripsTabs(tabs = tabs, pagerState = pagerState)
            MyTripsTabsContent(tabs = tabs, pagerState = pagerState)
        }

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyTripsTabs(tabs: List<MyTripTabItem>, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(

        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colorScheme.background,
        divider = {
            androidx.compose.material.TabRowDefaults.Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f)
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = MaterialTheme.colorScheme.primary,
                height = 2.dp
            )
        },
    ) {
        tabs.forEachIndexed { index, item ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                text = {
                    Text(
                        text = item.title,
                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onBackground

                    )
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground
            )
        }
    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyTripsTabsContent(tabs: List<MyTripTabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}

typealias ComposableFun = @Composable () -> Unit
sealed class MyTripTabItem( var title: String, var screen: ComposableFun) {
    object Upcoming : MyTripTabItem( "UPCOMING", { Upcoming() })
    object Completed : MyTripTabItem( "COMPLETED", { Completed() })
    object Cancelled : MyTripTabItem( "CANCELLED", { Cancelled() })
}