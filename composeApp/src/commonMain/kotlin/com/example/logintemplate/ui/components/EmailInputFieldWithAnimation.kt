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

fun isValidEmail(email: String): Boolean {
    return email.isNotEmpty()
            && email.contains("@")
            && email.contains(".")
            && email.indexOf("@") < email.lastIndexOf(".")
    // return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Composable
fun EmailInputFieldWithAnimation(
    email: String = "",
    onEmailChange: (String) -> Unit = {},
) {
    val isValid = isValidEmail(email)

    val errorColor by animateColorAsState(
        targetValue = if (email.isNotEmpty() && !isValid) Color.Red else Color.Transparent,
        animationSpec = tween(durationMillis = 500)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            isError = !isValid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            maxLines = 1
        )
        if (email.isNotEmpty()) {
            Text(
                text = if (isValid) "" else "Invalid email format",
                color = errorColor,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}