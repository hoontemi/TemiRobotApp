package com.ibtech.temirobotapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = TemiMint,
    onPrimary = TemiOnPrimary,
    primaryContainer = TemiMintContainer,
    onPrimaryContainer = TemiOnMintContainer,
    secondary = TemiMintDark,
    onSecondary = TemiOnPrimary,
    secondaryContainer = TemiTealContainer,
    onSecondaryContainer = TemiOnTealContainer,
    background = TemiBackground,
    surface = TemiSurface,
    onSurface = TemiOnSurface,
    surfaceVariant = TemiSurfaceVariant,
    onSurfaceVariant = TemiOnSurfaceVariant,
    outline = TemiOutline,
    outlineVariant = TemiOutlineVariant
)

private val DarkColors = darkColorScheme(
    primary = TemiMint,
    onPrimary = TemiOnPrimary,
    secondary = TemiMintDark
)

/**
 * 키오스크로 항상 밝은 화면에 고정한다.
 * 기기가 다크 모드로 바뀌어도 안내 화면 색이 달라지지 않아야 한다.
 */
private const val FORCE_LIGHT_THEME = true

@Composable
fun TemiRobotAppTheme(content: @Composable () -> Unit) {
    val colorScheme = if (!FORCE_LIGHT_THEME && isSystemInDarkTheme()) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TemiTypography,
        content = content
    )
}
