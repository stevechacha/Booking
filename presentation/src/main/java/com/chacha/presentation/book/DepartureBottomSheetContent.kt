package com.chacha.presentation.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.chacha.presentation.R
import com.chacha.presentation.book.components.PayBill
import com.chacha.presentation.book.components.PayBillItem
import com.chacha.presentation.common.components.RideOutlinedTextField

@Composable
fun DepartureBottomSheetContent(
    darkTheme: Boolean = false,
    onPayBillClick: (String) -> Unit,
    onBottomSheetClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) Color.DarkGray else Color.White)
    ) {

        var fromDeparture by remember { mutableStateOf("") }
        var toDestination by remember { mutableStateOf("") }

        val buyItems = listOf(
            PayBill(
                name = "John Doe",
                businessNumber = "071234567",
                image = R.drawable.arrow_back,
            ),
            PayBill(
                name = "John Doe",
                businessNumber = "071235678",
                image = null,
            )
        )


        RideOutlinedTextField(
            value = fromDeparture,
            onValueChange = {
                fromDeparture = it
            },
            keyboardType = KeyboardType.Phone,
            hint = stringResource(id = R.string.search),
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.padding(16.dp)

        )
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (darkTheme) Color.DarkGray else Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                buyItems.forEach { buyItem ->
                    PayBillItem(
                        payBill = buyItem,
                        onPayBillClick = {
                            fromDeparture = buyItem.name
                        }
                    )
                }

                buyItems.forEach { buyItem ->
                    PayBillItem(
                        payBill = buyItem,
                        onPayBillClick = {
                            toDestination = buyItem.name
                        }
                    )
                }
            }
        }
    }
}