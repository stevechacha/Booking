package com.chacha.presentation.auth.reset_password

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
import com.chacha.presentation.R
import com.chacha.presentation.common.components.AppOutlinedTextField
import com.chacha.presentation.common.components.AppToolbar
import com.chacha.presentation.common.components.ContinueButton
import com.chacha.presentation.common.theme.BookingTheme
import com.dev.chacha.presentation.auth.create_password.CreatePasswordViewModel

@Composable
fun ResetPasswordScreen(
    onClickAction: () -> Unit
) {
    ResetPasswordContent(
        viewModel = CreatePasswordViewModel(),
        onClickAction = onClickAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordContent(
    onClickAction: () -> Unit,
    viewModel: CreatePasswordViewModel
) {
    val (currentPassword, setCurrentPassword) = rememberSaveable { mutableStateOf("") }
    val (newPassword, setNewPassword) = rememberSaveable { mutableStateOf("") }
    val (confirmNewPassword, setConfirmNewPassword) = rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Reset Password",
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
                value = currentPassword,
                onValueChange = { setCurrentPassword(it) },
                hint = stringResource(id = R.string.current_password),
                keyboardType = KeyboardType.Password,
                error = viewModel.passwordError.value,
                isPasswordVisible = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            AppOutlinedTextField(
                value = newPassword,
                onValueChange = { setNewPassword(it) },
                keyboardType = KeyboardType.Password,
                hint = stringResource(id = R.string.new_password),
                error = viewModel.passwordError.value,
                isPasswordVisible = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            AppOutlinedTextField(
                value = confirmNewPassword,
                onValueChange = { setConfirmNewPassword(it) },
                keyboardType = KeyboardType.Password,
                hint = stringResource(id = R.string.confirmNewPassword),
                error = viewModel.passwordError.value,
                isPasswordVisible = viewModel.showConfirmPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowConfirmPassword(it)
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            ContinueButton(text = stringResource(id = R.string.continuee), onClick = onClickAction)
        }
    }


}

@Composable
@Preview
fun ResetPasswordScreenPreview() {
    BookingTheme {
        ResetPasswordScreen(
            onClickAction = {}
        )
    }
}
