package com.sailinghawklabs.triviaking.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TriviaKingTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamic: Boolean = Build.VERSION.SDK_INT >= 31,
    content: @Composable () -> Unit,
) {
    val colors = when {
        isDynamic && isDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
        isDynamic && !isDarkTheme -> dynamicLightColorScheme(LocalContext.current)
        isDarkTheme -> DarkThemeColors
        else -> LightThemeColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}