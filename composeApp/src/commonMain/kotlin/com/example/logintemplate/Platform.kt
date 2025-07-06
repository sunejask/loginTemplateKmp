package com.example.logintemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform