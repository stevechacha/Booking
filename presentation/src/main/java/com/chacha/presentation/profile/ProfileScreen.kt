package com.chacha.presentation.profile

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chacha.presentation.base.BottomSheets
import com.chacha.presentation.bus_seat.*
import com.chacha.presentation.common.navigation.GraphDestinations.SETTINGS_SHEET
import com.chacha.presentation.common.theme.ThemeMode
import com.chacha.presentation.common.theme.switchTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.chacha.presentation.R
import com.chacha.presentation.common.components.AppToolbar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(navController: NavController){
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()


    val uriHandler = LocalUriHandler.current

    val userName = "Stephen Chacha"
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.background
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding),
                    verticalArrangement = Arrangement.Top
                ) {

                    MoreVerticalItemWithCard(
                        drawable = R.drawable.ic_menu,
                        title = R.string.activate_chat_banking,
                        onClick = {
                            uriHandler.openUri("https://equitygroupholdings.com/ke")

                        },
                        showColorFilter = true
                    )
                    MoreVerticalItemWithCard(
                        drawable = R.drawable.ic_apply,
                        title = R.string.recommed,
                        onClick = {}
                    )
                    MoreVerticalItemWithCard(
                        drawable = R.drawable.airport_shuttle,
                        title = R.string.get_in_touch,
                        onClick = {
                        }
                    )
                    MoreVerticalItemWithCard(
                        drawable = R.drawable.ic_menu,
                        title = R.string.dark_mode,
                        showSwitcher = true,
                        onClick = {},
                        onCheckChange = {
                        },
                        isChecked = false
                    )
                    MoreVerticalItemWithCard(
                        drawable = R.drawable.ic_menu,
                        title = R.string.sign_out,
                        onClick = {
                        }
                    )


                }

            }
        },
        drawerState = drawerState,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Scaffold(
            topBar = {
                AppToolbar(
                    title = "Settings and more",
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                    onNavigateBack = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(start = 24.dp, end = 16.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            MoreVerticalItemWithCard(
                                drawable = R.drawable.ic_normal_seat,
                                title = R.string.notifications_title,
                                subtitle = R.string.notifications_subtitle,
                                onClick = {
                                }
                            )
                            MoreVerticalItemWithCard(
                                drawable = R.drawable.chair,
                                title = R.string.change_language_title,
                                onClick = {
                                }
                            )
                        }

                    }
                    item {
                        androidx.compose.material3.Text(
                            text = "Support",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            MoreVerticalItemWithCard(
                                drawable = R.drawable.ic_menu,
                                title = R.string.activate_chat_banking,
                                subtitle = R.string.activate_chat_banking_desc,
                                onClick = {
                                    uriHandler.openUri("https://equitygroupholdings.com/ke")

                                },
                                showColorFilter = true
                            )
                            MoreVerticalItemWithCard(
                                drawable = R.drawable.chair,
                                title = R.string.get_in_touch,
                                subtitle = R.string.get_in_touch_subtitle,
                                onClick = {
                                }
                            )


                        }

                    }

                    item {
                        androidx.compose.material3.Text(
                            text = "Security",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    item {
                        MoreVerticalItemWithCard(
                            drawable = R.drawable.ic_menu,
                            title = R.string.security_title,
                            subtitle = R.string.security_subtitle,
                            onClick = {
                            }
                        )
                    }

                    item {
                        androidx.compose.material3.Text(
                            text = "About us",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    item {
                        MoreVerticalItemWithCard(
                            drawable = R.drawable.ic_menu,
                            title = R.string.recommed,
                            subtitle = R.string.recommend_equity_to_friend_title,
                            onClick = {}
                        )
                    }

                    item {
                        Box {}
                    }

                }
            }

        }
    }

    fun handleSwitchTheme(mode: ThemeMode) {
        coroutineScope.launch {
            switchTheme(context, mode)
//            onClose()
        }
    }

}

fun handleSwitchTheme(mode: ThemeMode,context:Context,coroutineScope: CoroutineScope) {
    coroutineScope.launch {
        switchTheme(context, mode)
//            onClose()
    }
}
