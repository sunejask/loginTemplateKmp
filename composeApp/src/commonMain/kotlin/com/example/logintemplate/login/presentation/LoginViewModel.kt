package com.example.logintemplate.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logintemplate.login.domain.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private var _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnLoginClick -> {
                _state.update { it.copy(isLoading = true, errorMessage = "") }

                viewModelScope.launch(Dispatchers.IO) {
                    delay(2000)
                    try {
                        // Simulate a login attempt
                        val userName = action.userName
                        val password = action.password

                        val rememberData = action.rememberData

                        val result = loginRepository.login(userName, password, rememberData)

                        if (result.isSuccess) {
                            _state.update {
                                it.copy(
                                    isLoggedIn = true,
                                    isLoading = false,
                                    userName = userName,
                                    password = password,
                                    isRememberData = rememberData,
                                    errorMessage = ""
                                )
                            }
                        }

                        if (result.isFailure) {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = result.exceptionOrNull()?.message ?: ""
                                )
                            }
                        }
                    } catch (e: Exception) {
                        // Handle login error
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = e.message ?: "Login failed"
                            )
                        }
                    }
                }
            }

            is LoginAction.OnCheckAuthenticationStatus -> {
                _state.update { it.copy(isLoading = true, errorMessage = "") }
                viewModelScope.launch(Dispatchers.IO) {
                    delay(4000)
                    try {
                        val isAuthenticated = loginRepository.isAuthenticated()
                        val isRememberData = loginRepository.isRememberData()
                        val userData = loginRepository.fetchUserData()
                        println("app tes Is Authenticated: $isAuthenticated")
                        if (isAuthenticated) {
                            println("app tes User Name: ${userData.first}")
                            _state.update {
                                it.copy(
                                    isLoggedIn = true,
                                    isLoading = false,
                                    userName = userData.first,
                                    password = userData.second,
                                    isRememberData =
                                        isRememberData,
                                    errorMessage = ""
                                )
                            }
                        }
                        else {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    isLoggedIn = isAuthenticated,
                                    isLogOut = true,
                                    isRememberData = isRememberData,
                                    userName = userData.first,
                                    password = userData.second,
                                    errorMessage = ""
                                )
                            }
                        }
                    } catch (e: Exception) {
                        // _state.update { it.copy(isLoading = false, errorMessage = e.message ?: "Failed to check authentication status") }
                        // return@launch
                    }

                }
            }

            is LoginAction.OnLogOutClick -> {
                _state.update { it.copy(isLoading = true, errorMessage = "") }
                viewModelScope.launch(Dispatchers.IO) {
                    delay(2000)
                    try {
                        loginRepository.logout()
                        println("app tes logout false")
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isLoggedIn = false,
                                isLogOut = true,
                                errorMessage = ""
                            )
                        }
                    } catch (e: Exception) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = e.message ?: "Logout failed"
                            )
                        }
                    }
                }
            }
        }
    }
}