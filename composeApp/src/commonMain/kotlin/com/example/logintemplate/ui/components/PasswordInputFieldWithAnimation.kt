package com.example.logintemplate.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

fun isValidPassword(password: String): Boolean {
    return password.isNotEmpty() && password.length in 4..6 &&
            password.any { it.isDigit() } &&
            password.any { it.isUpperCase() }
}

@Composable
fun PasswordInputFieldWithAnimation(
    password: String = "",
    onPasswordChange: (String) -> Unit = {},
) {
    val isValid = isValidPassword(  password)

    val errorColor by animateColorAsState(
        targetValue = if (password.isNotEmpty() && !isValid) Color.Red else Color.Transparent,
        animationSpec = tween(durationMillis = 500)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            isError = !isValid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            maxLines = 1
        )
        if (password.isNotEmpty()) {
            Text(
                text = if (isValid) "" else "Invalid password format",
                color = errorColor,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}