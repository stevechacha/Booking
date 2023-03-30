package com.chacha.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chacha.presentation.R
import com.chacha.presentation.date_picker.DatePickerScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSendMoneyClicked: () -> Unit,
    onBuyAirtimeClicked: () -> Unit,
    onBuyGoodsClicked: () -> Unit,
    onPayBillClicked: () -> Unit,
    onWithdrawClicked: () -> Unit,
    onDepositClicked: () -> Unit,
    onLoanClicked: () -> Unit,
    onMarketClicked: () -> Unit,
    onSavingsClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            HomeToolbar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            TransactionCard(
                onSendMoneyClicked = onSendMoneyClicked,
                onBuyAirtimeClicked = onBuyAirtimeClicked,
                onBuyGoodsClicked = onBuyGoodsClicked,
                onPayBillClicked = onPayBillClicked,
                onWithdrawClicked = onWithdrawClicked,
                onDepositClicked = onDepositClicked
            )

            Spacer(modifier = Modifier.height(20.dp))

            PayWithRideSacco(
                onLoanClicked = onLoanClicked,
                onMarketClicked = onMarketClicked,
                onSavingsClicked = onSavingsClicked
            )
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))


        }

    }
}

@Composable
fun TransactionCard(
    onSendMoneyClicked: () -> Unit,
    onBuyAirtimeClicked: () -> Unit,
    onBuyGoodsClicked: () -> Unit,
    onPayBillClicked: () -> Unit,
    onWithdrawClicked: () -> Unit,
    onDepositClicked: () -> Unit,

    ) {
    
    Button(onClick = { }) {
        
    }


}

@Composable
fun PayWithRideSacco(
    onLoanClicked: () -> Unit,
    onMarketClicked: () -> Unit,
    onSavingsClicked: () -> Unit
) {


}





@Composable
fun HomeToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 20.dp, end = 20.dp, top = 19.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Welcome Back !")
            Text(text = "Stephen Chacha")
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "Current Balance",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.onSecondaryContainer)
        )
    }
}
