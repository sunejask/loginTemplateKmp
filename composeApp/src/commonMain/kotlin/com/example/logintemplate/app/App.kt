package com.example.logintemplate.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logintemplate.home.presentation.HomeScreenRoot
import com.example.logintemplate.login.presentation.LoginScreenRoot
import com.example.logintemplate.login.presentation.LoginViewModel
import com.example.logintemplate.splash.presentation.SplashScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    val startDestination = Route.Splash
    val viewModel = koinViewModel<LoginViewModel>()

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable<Route.Splash> {
                SplashScreenRoot(
                    viewModel = viewModel,
                    navigateToLogin = {
                        navController.navigate(Route.Login)
                    },
                    navigateToHome = {
                        navController.navigate(Route.Home)
                    }
                )
            }
            composable<Route.Login> {
                LoginScreenRoot(
                    viewModel = viewModel,
                    navigateToHome = {
                        navController.navigate(Route.Home)
                    }
                )
            }
            composable<Route.Home> {
                HomeScreenRoot(
                    viewModel = viewModel,
                    navigateToLogin = {
                        navController.navigate(Route.Login)
                    }
                )
            }
        }
    }
}