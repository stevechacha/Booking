package com.chacha.presentation.settings

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chacha.presentation.R
import com.chacha.presentation.base.BottomSheets

@SuppressLint("UnusedBoxWithConstraintsScope")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Settings() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,

    ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.settings_title),
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ThemeSwitcher()
                    About(Modifier.padding(start = 16.dp, end = 16.dp))
                }

            }
        }





}




@Composable
fun About(
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "About",
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
        )
        Text(
            text = "About Description",
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
        )
    }

}