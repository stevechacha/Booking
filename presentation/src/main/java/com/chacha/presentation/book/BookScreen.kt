package com.chacha.presentation.book

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.TabRowDefaults.Divider

import com.chacha.presentation.common.components.AppToolbar
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BookScreen() {
    val tabs = listOf(TabItem.OneWayBook, TabItem.ReturnsBook, TabItem.MultiCityBook)
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Book",
                showBackArrow = true
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
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
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object OneWayBook : TabItem("One Way", { OneWayBook() })
    object ReturnsBook : TabItem("Returns", { ReturnsBook() })

    @RequiresApi(Build.VERSION_CODES.O)
    object MultiCityBook : TabItem("MultiCity", { MultiCityBook() })
}