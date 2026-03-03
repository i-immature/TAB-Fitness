package com.tab.fitness.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class AppSkin { LiquidGlass, MaterialVibrant, OneUI, NothingGlyph }

private val liquidDark = darkColorScheme(
    primary = Color(0xFF87E6FF),
    secondary = Color(0xFF81D4FA),
    background = Color(0xFF05060A),
    surface = Color(0x331E88E5)
)

private val liquidLight = lightColorScheme(
    primary = Color(0xFF1156C4),
    secondary = Color(0xFF4472CA),
    background = Color(0xFFF6FAFF),
    surface = Color(0xAAFFFFFF)
)

private val vibrant = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC5),
    tertiary = Color(0xFFFF9800)
)

private val oneUi = lightColorScheme(
    primary = Color(0xFF0B57D0),
    secondary = Color(0xFF7B61FF),
    background = Color(0xFFF2F4F8)
)

private val nothing = darkColorScheme(
    primary = Color(0xFFEDEDED),
    secondary = Color(0xFFFF1A1A),
    background = Color(0xFF000000)
)

@Composable
fun TABTheme(skin: AppSkin, darkMode: Boolean, content: @Composable () -> Unit) {
    val scheme: ColorScheme = when (skin) {
        AppSkin.LiquidGlass -> if (darkMode || isSystemInDarkTheme()) liquidDark else liquidLight
        AppSkin.MaterialVibrant -> vibrant
        AppSkin.OneUI -> oneUi
        AppSkin.NothingGlyph -> nothing
    }
    MaterialTheme(colorScheme = scheme, content = content)
}
