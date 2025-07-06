package com.example.logintemplate.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.logintemplate.login.presentation.LoginAction
import com.example.logintemplate.login.presentation.LoginState
import com.example.logintemplate.login.presentation.LoginViewModel

@Composable
fun HomeScreenRoot(
    viewModel: LoginViewModel,
    navigateToLogin: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        viewModel = viewModel,
        navigateToLogin = navigateToLogin,
    )
}

@Composable
fun HomeScreen(
    state: LoginState,
    viewModel: LoginViewModel,
    navigateToLogin: () -> Unit,
) {
    LaunchedEffect(state) {
        println("app tes Home ${state.isLoggedIn} $state")
        if (!state.isLoading) {
            if (!state.isLoggedIn) {
                navigateToLogin()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Home Screen",
            modifier = Modifier.padding(top = 128.dp)
        )

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(top = 24.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("User name: ${state.userName}")
        }

        Button(
            onClick = { viewModel.onAction(LoginAction.OnLogOutClick) },
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
        ) {
            Text("logout")
        }
    }
}