package com.chacha.presentation.auth.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chacha.presentation.R
import com.chacha.presentation.common.components.AppOutlinedTextField
import com.chacha.presentation.common.components.AppToolbar
import com.chacha.presentation.common.components.ContinueButton
import com.chacha.presentation.common.theme.BookingTheme
import com.chacha.presentation.common.theme.PrimaryColor


@Composable
fun RegisterScreen(
    onClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    RegisterContent(
        onClick = onClick,
        onLoginClick = onLoginClick
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    onClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val (name, setName) = rememberSaveable { mutableStateOf("") }
    val (email, setEmail) = rememberSaveable { mutableStateOf("") }
    val (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
    val (idNumber, setIdNumber) = rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Register",
                showForwardArrow = true,
                showBackArrow = true
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AppOutlinedTextField(
                value = name,
                onValueChange = { setName(it) },
                hint = stringResource(id = R.string.name),
                keyboardType = KeyboardType.Text
            )

            AppOutlinedTextField(
                value = email,
                onValueChange = { setEmail(it) },
                hint = stringResource(id = R.string.email_hint),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            AppOutlinedTextField(
                value = mobileNumber,
                onValueChange = { setMobileNumber(it) },
                hint = stringResource(id = R.string.mobile_number_hint),
                keyboardType = KeyboardType.Phone
            )
            Spacer(modifier = Modifier.height(16.dp))

            AppOutlinedTextField(
                value = idNumber,
                onValueChange = { setIdNumber(it) },
                hint = stringResource(id = R.string.id_number_hint),
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(20.dp))
            ContinueButton(text = stringResource(id = R.string.sign_up), onClick = onClick)
            Spacer(modifier = Modifier.height(50.dp))

            SignIn(
                onLoginClick = onLoginClick
            )


        }

    }
}


@Composable
fun SignIn(
    onLoginClick: () -> Unit
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
            text = "Already have an account?",
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 5.dp)
        )
        Text(
            text = "SignIn",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable(onClick = onLoginClick),
            color = PrimaryColor

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
fun RegisterScreenPreview() {
    BookingTheme {
        RegisterScreen(
            onClick = {},
            onLoginClick = {}
        )
    }
}
