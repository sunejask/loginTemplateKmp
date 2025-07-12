package com.example.logintemplate.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.logintemplate.design_system.NoteMarkButton
import com.example.logintemplate.ui.components.EmailInputFieldWithAnimation
import com.example.logintemplate.ui.components.PasswordInputFieldWithAnimation
import com.example.logintemplate.ui.components.isValidEmail
import com.example.logintemplate.ui.components.isValidPassword

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        viewModel = viewModel,
        navigateToHome = navigateToHome
    )
}

// Isolated Composable function for the login screen
@Composable
fun LoginScreen(
    state: LoginState,
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit
) {
    var userNameInput by remember { mutableStateOf("") }

    var passwordInput by remember { mutableStateOf("") }

    val previousState = remember { state }

    var isChecked by remember { mutableStateOf(false) }

    LaunchedEffect(state.isLoggedIn) {
        if (previousState != state && state.isLoggedIn) {
            navigateToHome()
        }
    }

    LaunchedEffect(Unit) {
        if (state.isRememberData) {
            userNameInput = state.userName
            passwordInput = state.password
            isChecked = state.isRememberData
        }
    }

    val isLoginEnabled = isValidEmail(userNameInput) && isValidPassword(passwordInput)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Spacer(modifier = Modifier.padding(32.dp))
        Text(
            "Login Screen",
            modifier = Modifier.padding(top = 128.dp)
        )
       EmailInputFieldWithAnimation(
            email = userNameInput,
            onEmailChange = { userNameInput = it },
        )

        PasswordInputFieldWithAnimation (
            password = passwordInput,
            onPasswordChange = { passwordInput = it }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 6.dp)
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Text("Remember me")
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(top = 24.dp)
            )
        }

        if (state.errorMessage.isNotEmpty()) {
            Text(
                text = state.errorMessage, color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.padding(top = 24.dp)
            )
        }

        NoteMarkButton(
            text = "Log In",
            onClick = {
                viewModel.onAction(
                    LoginAction.OnLoginClick(
                        userNameInput,
                        passwordInput,
                        isChecked
                    )
                )
            },
            enabled = isLoginEnabled,
            modifier = Modifier.fillMaxWidth()
        )
        /*
        Button(
            onClick = {
                viewModel.onAction(
                    LoginAction.OnLoginClick(
                        userNameInput,
                        passwordInput,
                        isChecked
                    )
                )
            },
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            enabled = isLoginEnabled
        ) {
            Text("Login")
        }
        */
    }

}