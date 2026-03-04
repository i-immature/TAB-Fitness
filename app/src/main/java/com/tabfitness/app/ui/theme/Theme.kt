package com.tabfitness.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class AppSkin { LIQUID_GLASS, MATERIAL, ONE_UI, NOTHING }

private val LiquidDark = darkColorScheme(
    primary = Color(0xFF86E4FF),
    secondary = Color(0xFFB3C7FF),
    background = Color.Black,
    surface = Color(0xFF10131A)
)

private val MaterialLight = lightColorScheme()

private val OneUiDark = darkColorScheme(
    primary = Color(0xFF8AB4F8),
    secondary = Color(0xFF7FD7C5),
    background = Color(0xFF111216),
    surface = Color(0xFF1B1D24)
)

private val NothingDark = darkColorScheme(
    primary = Color(0xFFFFFFFF),
    secondary = Color(0xFFE53935),
    background = Color(0xFF000000),
    surface = Color(0xFF151515)
)

@Composable
fun TabTheme(skin: AppSkin, darkMode: Boolean, content: @Composable () -> Unit) {
    val systemDark = isSystemInDarkTheme()
    val useDark = darkMode || systemDark
    val scheme: ColorScheme = when (skin) {
        AppSkin.LIQUID_GLASS -> if (useDark) LiquidDark else lightColorScheme(primary = Color(0xFF0B5D7A))
        AppSkin.MATERIAL -> if (useDark) darkColorScheme() else MaterialLight
        AppSkin.ONE_UI -> if (useDark) OneUiDark else lightColorScheme(primary = Color(0xFF1E5AA8))
        AppSkin.NOTHING -> if (useDark) NothingDark else lightColorScheme(primary = Color(0xFF0A0A0A), secondary = Color(0xFFE53935))
    }

    MaterialTheme(
        colorScheme = scheme,
        content = content
    )
}
