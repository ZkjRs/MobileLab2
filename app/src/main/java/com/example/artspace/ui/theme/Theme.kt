package com.example.artspace.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6B4E71),
    onPrimary = Color.White,
    secondary = Color(0xFF9A8C98),
    background = Color(0xFFF2E9E4),
    surface = Color(0xFFF2E9E4),
    onBackground = Color(0xFF22223B),
    onSurface = Color(0xFF22223B),
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFC9ADA7),
    onPrimary = Color(0xFF22223B),
    secondary = Color(0xFF9A8C98),
    background = Color(0xFF22223B),
    surface = Color(0xFF22223B),
    onBackground = Color(0xFFF2E9E4),
    onSurface = Color(0xFFF2E9E4),
)

@Composable
fun ArtSpaceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
