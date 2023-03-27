package com.chacha.presentation.book

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chacha.presentation.R
import com.chacha.presentation.book.components.BookCard
import com.chacha.presentation.book.components.DatePickerCard
import com.chacha.presentation.book.components.PayBill
import com.chacha.presentation.book.components.PayBillItem
import com.chacha.presentation.common.components.ContinueButton
import com.chacha.presentation.common.components.RideOutlinedTextField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun OneWayBook(
    darkTheme: Boolean = isSystemInDarkTheme(),
    ) {
    var fromDeparture by remember { mutableStateOf("") }
    var toDestination by remember { mutableStateOf("") }

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val scope = rememberCoroutineScope()

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

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent ={
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(if (darkTheme) Color.DarkGray else Color.White)
            ) {
                RideOutlinedTextField(
                    value = fromDeparture,
                    onValueChange = {
                        fromDeparture = it
                    },
                    keyboardType = KeyboardType.Phone,
                    hint = stringResource(id = R.string.search),
                    trailingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            androidx.compose.material3.Icon(
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

        },
        sheetPeekHeight = 0.dp,
        backgroundColor = MaterialTheme.colorScheme.background

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {

            BookCard(
                fromTitle = stringResource(id = R.string.from) ,
                toTitle = stringResource(id = R.string.to),
                from = fromDeparture,
                fromHint = R.string.from_hint,
                toHint = R.string.to_hint,
                onFromClick = {
                    scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                },
                to = toDestination,
                onToClick = {
                    scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            DatePickerCard(
                departureTitle =  stringResource(id = R.string.departure),
                returnsTitle = stringResource(id = R.string.returns),
                departureDate = "12/12/2021",
                returnDate = "" ,
                numberOfPassenger= "1" ,
                vehicleType = "Car",
                onDepartureDateClick = { /*TODO*/ },
                onReturnDateClick = { /*TODO*/ },
                onPassengerClick = { /*TODO*/ }) {

            }

            Spacer(modifier =Modifier.height(20.dp))

            ContinueButton(
                text = stringResource(id = R.string.continuee),
                onClick = {}
            )
        }

    }

    
}