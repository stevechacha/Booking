package com.chacha.presentation.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chacha.presentation.R
import com.chacha.presentation.common.components.AppOutlinedTextField
import com.chacha.presentation.common.components.AppToolbar
import com.chacha.presentation.common.components.ContinueButton
import com.chacha.presentation.common.theme.BookingTheme
import com.chacha.presentation.common.theme.PrimaryColor
import com.dev.chacha.presentation.auth.login.LoginUiEvents
import com.dev.chacha.presentation.auth.login.LoginViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(),
) {
    LoginContent(
        onClick = onClick,
        onSignUpClick = onSignUpClick,
        onForgotClick = onForgotClick,
        viewModel = viewModel,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    onClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit,
    viewModel: LoginViewModel,
) {
    val usernameState = viewModel.usernameState.value
    val passwordState = viewModel.passwordState.value
    val loginState = viewModel.loginState.value

    val snackBarHostState = remember { SnackbarHostState() }
    val localCoroutineScope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is LoginUiEvents.SnackBarEvent -> {
                    localCoroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = "Error message",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
                is LoginUiEvents.NavigateEvent -> {
                    event.route
                    onClick()
                    localCoroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = "Login successful",
                            duration = SnackbarDuration.Short
                        )
                    }

                }
            }
        }
    }

    Scaffold(
        topBar = {
            AppToolbar(
                title = "Login",
                showBackArrow = true,
                showForwardArrow = true
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(vertical = 16.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val text by rememberSaveable { mutableStateOf("") }
            val charLimit = 10

            AppOutlinedTextField(
                value = usernameState.text,
                onValueChange = {
                    viewModel.setUsername(it)
                },
                keyboardType = KeyboardType.Email,
                hint = stringResource(id = R.string.email_or_mobile_number),
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icons.Filled.Home
                    }
                },
                supportingText = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Limit: ${usernameState.text.length}/$charLimit",
                        textAlign = TextAlign.End,
                    )
                }
            )


            if (usernameState.error != "") {
                Text(
                    text = usernameState.error,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            AppOutlinedTextField(
                value = passwordState.text,
                onValueChange = { viewModel.setPassword(it) },
                keyboardType = KeyboardType.Password,
                hint = stringResource(id = R.string.password),
                isPasswordVisible = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                },
            )
            if (passwordState.error != "") {
                Text(
                    text = passwordState.error,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onForgotClick() },
                text = "Forgot Password?",
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.End,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(30.dp))
            ContinueButton(
                text = stringResource(id = R.string.submit),
                onClick = onClick
            )
            Spacer(modifier = Modifier.height(50.dp))

            SignUp(
                onSignUpClick = onSignUpClick
            )
        }


    }

}

@Composable
fun SignUp(
    onSignUpClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Divider(
            modifier = Modifier.width(80.dp)
        )
        Text(
            text = "Don't have an account?",
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 5.dp)
        )
        Text(
            text = "SignUp",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 2.dp)
                .clickable(onClick = onSignUpClick),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,

        )
        Divider(
            modifier = Modifier
                .width(80.dp)
                .padding(start = 5.dp),

            )

    }

}

@Composable
@Preview
fun LoginScreenPreview() {
    BookingTheme {
        LoginScreen(
            onClick = { /*TODO*/ },
            onSignUpClick = { /*TODO*/ },
            onForgotClick = { /*TODO*/ },
        )

    }
}
