package com.example.logintemplate.login.presentation

sealed interface LoginAction {
    data class OnLoginClick(val userName: String, val password: String, val rememberData: Boolean) : LoginAction
    data object OnLogOutClick : LoginAction
    data object OnCheckAuthenticationStatus: LoginAction
}