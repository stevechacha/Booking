package com.chacha.presentation.book.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chacha.presentation.common.components.AppToolbar
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@Composable
fun BookingBottomSheet(
    title: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    onDismiss: () -> Unit,
    itemTo: List<String> = emptyList(),
    onItemToSelected:(String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp)
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
        )
        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
            thickness = 0.5.dp,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                    .clickable { onItemSelected(item) }
                    .heightIn(min = 48.dp)
            ) {
                Text(
                    text = item,
                    modifier = Modifier.weight(1f)
                )
                if (item == selectedItem) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                    )
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                thickness = 0.5.dp
            )
        }
        items.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                    .clickable { onItemToSelected(it)}
                    .heightIn(min = 48.dp)
            ) {
                Text(
                    text = it,
                    modifier = Modifier.weight(1f)
                )
                if (it == selectedItem) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                    )
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                thickness = 0.5.dp
            )

        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            TextButton(
                onClick = { onDismiss() },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(text = "Close")
            }
        }
    }
}

@Composable
@Preview
fun BookingBottomsheetPrevie() {
    BookingBottomSheet(
        title = "Select a country",
        items = listOf("Vietnam", "Japan", "Korea", "China", "Thailand"),
        selectedItem = "Vietnam",
        onItemSelected = {},
        onDismiss = {},
        itemTo = listOf("Vietnam", "Japan", "Korea", "China", "Thailand"),
        onItemToSelected = {},
    )
}
