package com.tab.fitness.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tab.fitness.ui.theme.AppSkin

@Composable
fun HamburgerDrawer(
    darkMode: Boolean,
    onDarkModeChanged: (Boolean) -> Unit,
    selectedSkin: AppSkin,
    onSkinSelect: (AppSkin) -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(0.82f).fillMaxHeight().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Menu")
        Text("Dark Mode")
        Switch(checked = darkMode, onCheckedChange = onDarkModeChanged)
        Text("Add Watch & Sync")
        Text("Sync workout and sleep data", modifier = Modifier.clickable { })
        HorizontalDivider()
        Text("Settings")
        Text("• App Theme Selector")
        ThemeOption(AppSkin.LiquidGlass, selectedSkin, onSkinSelect)
        ThemeOption(AppSkin.MaterialVibrant, selectedSkin, onSkinSelect)
        ThemeOption(AppSkin.OneUI, selectedSkin, onSkinSelect)
        ThemeOption(AppSkin.NothingGlyph, selectedSkin, onSkinSelect)
        Text("• Ringtone or Reminder control")
        Text("• App Version: 1.0.0", modifier = Modifier.align(Alignment.CenterHorizontally))
        Text("Logout", modifier = Modifier.clickable { onLogout() })
        Text("Linktree: https://linktr.ee/immature.ig", modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
private fun ThemeOption(item: AppSkin, selectedSkin: AppSkin, onSkinSelect: (AppSkin) -> Unit) {
    val marker = if (item == selectedSkin) "✓" else "○"
    Text("$marker ${item.name}", modifier = Modifier.clickable { onSkinSelect(item) })
}
