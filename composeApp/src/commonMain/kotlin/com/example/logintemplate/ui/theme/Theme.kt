package com.example.logintemplate.ui.theme

import com.example.logintemplate.ui.Background
import com.example.logintemplate.ui.OnSurface
import com.example.logintemplate.ui.OnSurfaceVariant
import com.example.logintemplate.ui.Primary
import com.example.logintemplate.ui.Surface
import com.example.logintemplate.ui.SurfaceLowest
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.logintemplate.ui.BackgroundDark
import com.example.logintemplate.ui.OnSurfaceDark
import com.example.logintemplate.ui.PrimaryDark

val LightColorTheme = lightColorScheme(
    primary = Primary,
    surface = Surface,
    surfaceContainerLowest = SurfaceLowest,
    background = Background,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant
)

val DarkColorTheme = lightColorScheme(
    primary = PrimaryDark,
    surface = Surface,
    surfaceContainerLowest = SurfaceLowest,
    background = BackgroundDark,
    onSurface = OnSurfaceDark,
    onSurfaceVariant = OnSurfaceVariant

       // checkedColor = MaterialTheme.colorScheme.primary,
       // uncheckedColor = MaterialTheme.colorScheme.onSurface,
       // checkmarkColor = MaterialTheme.colorScheme.background
)

@Composable
fun extendedColor(light: Color, dark: Color): Color {
    return if(isSystemInDarkTheme()) dark else light
}

val ColorScheme.extraColor: Color @Composable get() = extendedColor(
    light = Color(0xFF000000),
    dark = Color(0xFFFFFFFF)
)

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(5.dp),
    medium = RoundedCornerShape(15.dp)
)

@Composable
fun NoteMarkTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme())  DarkColorTheme else LightColorTheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}