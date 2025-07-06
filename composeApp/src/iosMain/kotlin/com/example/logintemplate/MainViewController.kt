package com.example.logintemplate

import androidx.compose.ui.window.ComposeUIViewController
import com.example.logintemplate.app.App
import com.example.logintemplate.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}