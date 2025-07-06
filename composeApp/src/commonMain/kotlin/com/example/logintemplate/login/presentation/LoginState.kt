package com.example.logintemplate.login.presentation

// MVI architecture
data class LoginState (
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isLogOut: Boolean = false,
    val isRememberData: Boolean = false,
    val errorMessage: String = "",
    val userName: String = "",
    val password: String = ""
)