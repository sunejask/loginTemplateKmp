package com.example.logintemplate.login.domain

interface LoginRepository {
    suspend fun login(userName: String, password: String, rememberData: Boolean): Result<Unit>
    suspend fun logout(): Result<Unit>
    suspend fun isAuthenticated(): Boolean
    suspend fun fetchUserData(): Pair<String, String>
    suspend fun isRememberData(): Boolean
}