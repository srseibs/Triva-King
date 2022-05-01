package com.sailinghawklabs.triviaking.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val buttonHeight: Dp = 48.dp,
    val textFieldHeight: Dp = 48.dp,
    val buttonCornerRadius: Float = 50f,
    val screenPadding: Dp = 24.dp,
    val listPadding: Dp = 16.dp,
    val formSpacing: Dp = 48.dp,
    val toolbarHeight: Dp = 56.dp,
    val innerToolbarPadding: Dp = 4.dp,
)

val LocalDimensions = compositionLocalOf { Dimensions() }
