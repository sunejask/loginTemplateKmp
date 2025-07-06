package com.example.logintemplate.splash.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.logintemplate.login.presentation.LoginAction
import com.example.logintemplate.login.presentation.LoginState
import com.example.logintemplate.login.presentation.LoginViewModel

@Composable
fun SplashScreenRoot(
    viewModel: LoginViewModel,
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SplashScreen(
        state = state,
        viewModel = viewModel,
        navigateToLogin = navigateToLogin,
        navigateToHome = navigateToHome
    )
}

@Composable
fun SplashScreen(
    state: LoginState,
    viewModel: LoginViewModel,
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit
) {
    val previousState = remember { state }

    LaunchedEffect(Unit) {
        println("app tes Splash LaunchedEffect")
        viewModel.onAction(LoginAction.OnCheckAuthenticationStatus)
    }

    LaunchedEffect(state.isLoading) {
        println("app tes Splash ${state.isLoading} ${state.isLoggedIn} ${state.isLogOut}")
        if (!state.isLoading && previousState != state) {
            if (state.isLoggedIn) {
                navigateToHome()
            }

            if (!state.isLoggedIn) {
                navigateToLogin()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Welcome to the new App",
            modifier = Modifier.padding(top = 128.dp)
        )

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(top = 24.dp)
            )
        }
    }
}