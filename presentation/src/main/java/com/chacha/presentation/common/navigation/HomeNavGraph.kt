package com.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chacha.presentation.R
import com.chacha.presentation.book.BookScreen
import com.chacha.presentation.home.HomeScreen
import com.chacha.presentation.parcel.ParcelScreen
import com.chacha.presentation.profile.ProfileScreen
import com.chacha.presentation.trips.MyTripScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun HomeNavGraph(
    navController: NavHostController,
    showBottomBar: (Boolean) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route ,
        route = Graph.HOME
    ){

        composable(BottomBarScreen.Home.route){
            showBottomBar(true)
            HomeScreen(
                onSendMoneyClicked = { navController.navigate(HomeAction.SendMoney.route) },
                onBuyAirtimeClicked = { navController.navigate(HomeAction.BuyAirtime.route) },
                onBuyGoodsClicked = { navController.navigate(HomeAction.BuyGoods.route) },
                onPayBillClicked = { navController.navigate(HomeAction.PayWithSacco.route) },
                onWithdrawClicked = { navController.navigate(HomeAction.Withdraw.route) },
                onDepositClicked = { navController.navigate(HomeAction.Deposit.route) },
                onLoanClicked = {  navController.navigate(HomeAction.Loan.route) },
                onMarketClicked = { navController.navigate(BottomBarScreen.Parcel.route) },
                onSavingsClicked = { navController.navigate(HomeAction.Savings.route) },
        )

        }
        composable(BottomBarScreen.Book.route){
            showBottomBar(false)
            BookScreen()

        }
        composable(BottomBarScreen.MyTrips.route){
            showBottomBar(true)
            MyTripScreen()


        }
        composable(BottomBarScreen.Parcel.route){
            showBottomBar(true)
            ParcelScreen()
        }

        composable(BottomBarScreen.Profile.route){
            showBottomBar(true)
            ProfileScreen()

        }

       composable(HomeAction.SendMoney.route){
            showBottomBar(false)

        }
        composable(HomeAction.BuyAirtime.route){
            showBottomBar(false)
        }
        composable(HomeAction.BuyGoods.route){
            showBottomBar(false)
        }

        composable(HomeAction.Withdraw.route){
            showBottomBar(false)
        }
        composable(HomeAction.Deposit.route){
            showBottomBar(false)
        }

        composable(HomeAction.PayWithSacco.route){
            showBottomBar(false)
        }
        composable(HomeAction.Savings.route){
            showBottomBar(false)
        }

        detailsNavGraph(navController = navController)

    }

}



fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = HomeAction.SendMoney.route
    ) {

    }
}

sealed class HomeAction(val route: String) {
    object SendMoney : HomeAction(route = "send_money")
    object BuyGoods : HomeAction(route = "buy_goods")
    object BuyAirtime : HomeAction(route = "buy airtime")
    object PayWithSacco: HomeAction(route = "pay_with_sacco")
    object Savings : HomeAction(route = "Saving")
    object Withdraw : HomeAction(route = "Withdraw")
    object Deposit: HomeAction(route = "deposit")
    object Loan: HomeAction("MyTrips")
}


sealed class BottomBarScreen(val route: String, @DrawableRes val icon: Int, val title: String){
    object Home: BottomBarScreen("home", R.drawable.search_book, "Home")
    object Book: BottomBarScreen("transaction",R.drawable.search_book,"Book")
    object MyTrips: BottomBarScreen("loan",R.drawable.search_book,"My Trips")
    object Parcel: BottomBarScreen("market",R.drawable.search_book,"Parcel")
    object Profile: BottomBarScreen("account",R.drawable.profile_account,"Profile")
}

val bottomNavigationItems = listOf(
    BottomBarScreen.Home,
    BottomBarScreen.Book,
    BottomBarScreen.MyTrips,
    BottomBarScreen.Parcel,
    BottomBarScreen.Profile
)