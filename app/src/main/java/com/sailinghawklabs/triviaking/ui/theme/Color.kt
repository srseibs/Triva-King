package com.sailinghawklabs.triviaking.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


// https://material-foundation.github.io/material-theme-builder/#/custom
// primary: 3 181 133
// secondary: 0 65 35
// tertiary: 125 82 96
// neutral: 96 93 98

val md_theme_light_primary = Color(0xFF006c4c)
val md_theme_light_onPrimary = Color(0xFFffffff)
val md_theme_light_primaryContainer = Color(0xFF70fbc6)
val md_theme_light_onPrimaryContainer = Color(0xFF002115)
val md_theme_light_secondary = Color(0xFF006d3c)
val md_theme_light_onSecondary = Color(0xFFffffff)
val md_theme_light_secondaryContainer = Color(0xFF96f7b8)
val md_theme_light_onSecondaryContainer = Color(0xFF00210e)
val md_theme_light_tertiary = Color(0xFF7D5260)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFFFD8E4)
val md_theme_light_onTertiaryContainer = Color(0xFF31111D)
val md_theme_light_error = Color(0xFFB3261E)
val md_theme_light_errorContainer = Color(0xFFF9DEDC)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410E0B)
val md_theme_light_background = Color(0xFFFFFBFE)
val md_theme_light_onBackground = Color(0xFF1C1B1F)
val md_theme_light_surface = Color(0xFFFFFBFE)
val md_theme_light_onSurface = Color(0xFF1C1B1F)
val md_theme_light_surfaceVariant = Color(0xFFE7E0EC)
val md_theme_light_onSurfaceVariant = Color(0xFF49454F)
val md_theme_light_outline = Color(0xFF79747E)
val md_theme_light_inverseOnSurface = Color(0xFFF4EFF4)
val md_theme_light_inverseSurface = Color(0xFF313033)
val md_theme_light_inversePrimary = Color(0xFF4fdeaa)
val md_theme_light_shadow = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFF4fdeaa)
val md_theme_dark_onPrimary = Color(0xFF003826)
val md_theme_dark_primaryContainer = Color(0xFF005138)
val md_theme_dark_onPrimaryContainer = Color(0xFF70fbc6)
val md_theme_dark_secondary = Color(0xFF7ada9d)
val md_theme_dark_onSecondary = Color(0xFF00391d)
val md_theme_dark_secondaryContainer = Color(0xFF00522c)
val md_theme_dark_onSecondaryContainer = Color(0xFF96f7b8)
val md_theme_dark_tertiary = Color(0xFFEFB8C8)
val md_theme_dark_onTertiary = Color(0xFF492532)
val md_theme_dark_tertiaryContainer = Color(0xFF633B48)
val md_theme_dark_onTertiaryContainer = Color(0xFFFFD8E4)
val md_theme_dark_error = Color(0xFFF2B8B5)
val md_theme_dark_errorContainer = Color(0xFF8C1D18)
val md_theme_dark_onError = Color(0xFF601410)
val md_theme_dark_onErrorContainer = Color(0xFFF9DEDC)
val md_theme_dark_background = Color(0xFF1C1B1F)
val md_theme_dark_onBackground = Color(0xFFE6E1E5)
val md_theme_dark_surface = Color(0xFF1C1B1F)
val md_theme_dark_onSurface = Color(0xFFE6E1E5)
val md_theme_dark_surfaceVariant = Color(0xFF49454F)
val md_theme_dark_onSurfaceVariant = Color(0xFFCAC4D0)
val md_theme_dark_outline = Color(0xFF938F99)
val md_theme_dark_inverseOnSurface = Color(0xFF1C1B1F)
val md_theme_dark_inverseSurface = Color(0xFFE6E1E5)
val md_theme_dark_inversePrimary = Color(0xFF006c4c)
val md_theme_dark_shadow = Color(0xFF000000)

val seed = Color(0xFF6750A4)
val error = Color(0xFFB3261E)

val ColorScheme.AppGreenPass: Color
   get() = md_theme_light_secondary
