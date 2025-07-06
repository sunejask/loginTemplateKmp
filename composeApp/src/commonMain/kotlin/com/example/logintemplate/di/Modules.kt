package com.example.logintemplate.di

import com.example.logintemplate.login.data.LocalLoginRepository
import com.example.logintemplate.login.domain.LoginRepository
import com.example.logintemplate.login.presentation.LoginViewModel
import com.russhwolf.settings.Settings
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    single<Settings> { Settings() }
    singleOf(::LocalLoginRepository).bind<LoginRepository>()
    viewModelOf(::LoginViewModel)
}