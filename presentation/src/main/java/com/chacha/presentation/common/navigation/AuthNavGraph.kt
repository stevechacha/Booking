package com.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chacha.presentation.auth.create_password.CreatePasswordScreen
import com.chacha.presentation.auth.forgot.ForgotPasswordScreen
import com.chacha.presentation.auth.login.LoginScreen
import com.chacha.presentation.auth.register.RegisterScreen
import com.chacha.presentation.auth.welcome.WelcomeScreen
import com.chacha.presentation.common.navigation.GraphDestinations.AUTHENTICATION
import com.chacha.presentation.common.navigation.GraphDestinations.HOME_ROUTE
import com.chacha.presentation.onboard.OnBoardScreen


@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = AUTHENTICATION,
        startDestination = AuthScreen.Onboard.route
    ) {
        composable(AuthScreen.Onboard.route) {
            OnBoardScreen(
                onClickAction = {
                    navController.navigate(AuthScreen.Welcome.route)
                },
                navController = navController
            )

        }
        composable(AuthScreen.Welcome.route) {
            WelcomeScreen(
                onSignUp = {
                    navController.navigate(AuthScreen.Register.route)
                },
                onLogin = {
                    navController.navigate(AuthScreen.Login.route)
                }
            )
        }
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(HOME_ROUTE)
                },
                onSignUpClick = {
                    navController.navigate(AuthScreen.Register.route)
                },
                onForgotClick = {
                    navController.navigate(AuthScreen.Forgot.route)
                },
            )
        }
        composable(route = AuthScreen.Register.route) {
            RegisterScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(AuthScreen.CreatePassword.route)
                },
                onLoginClick = {
                    navController.navigate(AuthScreen.Login.route)
                }
            )
        }

        composable(route = AuthScreen.CreatePassword.route) {
            CreatePasswordScreen(
                onClickAction = {
                    navController.popBackStack()
                    navController.navigate(HOME_ROUTE)
                }
            )
        }
        composable(route = AuthScreen.Forgot.route) {
            ForgotPasswordScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(HOME_ROUTE)
                },
                onLoginClick = {
                    navController.navigate(AuthScreen.Login.route)
                }
            )
        }
    }
}


sealed class AuthScreen(val route: String) {
    object Onboard : AuthScreen("onboard_route")
    object Welcome : AuthScreen("welcome")
    object Login : AuthScreen(route = "LOGIN")
    object PinLock : AuthScreen("PinLock")
    object Register : AuthScreen(route = "REGISTER")
    object Forgot : AuthScreen(route = "FORGOT")
    object CreatePassword : AuthScreen(route = "CREATE_ACCOUNT")
}