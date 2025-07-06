package com.example.logintemplate

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.logintemplate.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "LoginTemplate",
    ) {
        App()
    }
}