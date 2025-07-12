package com.example.logintemplate.sandbox.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RowColumn (modifier: Modifier = Modifier)
{
    Row(modifier = Modifier.fillMaxSize()) {
        Text("Column 1",)
    }
}

@Preview (
   // showBackground = true,
    // backgroundColor = 0xFFFFFFFF,
)
@Composable
private fun RowColumnPreview() {
    // JetpackComposeSandboxTheme {
        RowColumn()
    // }
}