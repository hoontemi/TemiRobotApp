package com.ibtech.temirobotapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = TemiMint,
    onPrimary = TemiOnPrimary,
    secondary = TemiMintDark,
    background = TemiBackground,
    surface = TemiSurface,
    onSurface = TemiOnSurface
)

private val DarkColors = darkColorScheme(
    primary = TemiMint,
    onPrimary = TemiOnPrimary,
    secondary = TemiMintDark
)

@Composable
fun TemiRobotAppTheme(content: @Composable () -> Unit) {
    val colorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TemiTypography,
        content = content
    )
}
