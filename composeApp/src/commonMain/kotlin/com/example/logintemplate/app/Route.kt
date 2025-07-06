package com.example.logintemplate.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Splash: Route

    @Serializable
    data object Login: Route

    @Serializable
    data object Home : Route
}