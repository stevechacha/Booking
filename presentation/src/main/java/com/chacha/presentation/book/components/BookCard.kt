package com.chacha.presentation.book.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chacha.presentation.R
import com.chacha.presentation.common.theme.Border
import com.chacha.presentation.common.theme.mainBackground

@Composable
fun BookCard(
    fromTitle: String,
    toTitle: String,
    from: String?,
    to: String?,
    @StringRes fromHint: Int,
    onFromClick: () -> Unit,
    onToClick: () -> Unit,
    @StringRes toHint: Int,

    ) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onFromClick),
        border = BorderStroke(1.dp, Border),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(3.dp),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = fromTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clickable(onClick = onFromClick),
                color = MaterialTheme.colorScheme.onBackground
            )
             if (from==null || from.isEmpty()){
                 Text(
                     text = stringResource(id = fromHint),
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(top = 8.dp)
                         .clickable(onClick =  onFromClick),
                     fontSize = 24.sp
                 )
             } else{
                 Text(
                     text = from,
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(top = 8.dp)
                         .clickable(onClick =  onFromClick),
                     fontSize = 24.sp

                 )
             }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth(0.91f),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.search_book),
                     contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )

            }
            Text(
                text = toTitle,
                modifier = Modifier.padding( top = 16.dp)
            )
            if (to==null || to.isEmpty()){
                Text(
                    text = stringResource(id = toHint),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable(onClick =  onToClick),
                    fontSize = 24.sp

                )
            } else{
                Text(
                    text = to,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable(onClick =  onToClick),
                    fontSize = 24.sp

                )

            }
        }

    }

}

@Composable
@Preview
fun BookCardPreview() {

}