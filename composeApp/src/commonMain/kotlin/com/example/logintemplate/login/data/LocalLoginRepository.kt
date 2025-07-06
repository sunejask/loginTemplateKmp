package com.example.logintemplate.login.data

import com.example.logintemplate.login.domain.LoginRepository
import com.russhwolf.settings.Settings

class LocalLoginRepository(
    private val settings: Settings
) : LoginRepository {
    override suspend fun login(
        userName: String,
        password: String,
        rememberData: Boolean
    ): Result<Unit> {
        return if (userName == "test@test.pl" && password == "test4A") {
            // Add here try catch block to handle exceptions if needed
            settings.putBoolean("isLoggedIn", true)
            settings.putString("username", userName)
            settings.putString("password", password)
            settings.putBoolean("rememberData", rememberData)
            Result.success(Unit)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun logout(): Result<Unit> {
        // Add here try catch block to handle exceptions if needed
        settings.remove("isLoggedIn")
        return Result.success(Unit)
    }

    override suspend fun isAuthenticated(): Boolean {
        return settings.getBoolean("isLoggedIn", false)
    }

    override suspend fun fetchUserData(): Pair<String, String> {
        val username = settings.getString("username", "")
        val password = settings.getString("password", "")
        return Pair(username, password)
    }

    override suspend fun isRememberData(): Boolean {
        return settings.getBoolean("rememberData", false)
    }
}