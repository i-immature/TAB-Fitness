package com.tab.fitness.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabTopBar(content: @Composable () -> Unit) {
    val drawer = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = androidx.compose.runtime.rememberCoroutineScope()
    var dark by remember { mutableStateOf(true) }

    ModalNavigationDrawer(
        drawerState = drawer,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Dark Mode")
                    Switch(checked = dark, onCheckedChange = { dark = it })
                    TextButton(onClick = {}) { Text("Add Watch + Sync") }
                    TextButton(onClick = {}) { Text("Settings (Theme + Ringtone)") }
                    TextButton(onClick = {}) { Text("Logout") }
                    Text("Linktree: https://linktr.ee/immature.ig", modifier = Modifier.padding(top = 20.dp))
                }
            }
        }
    ) {
        Column {
            CenterAlignedTopAppBar(
                title = { Text("TAB") },
                navigationIcon = { IconButton(onClick = {}) { Icon(Icons.Default.Person, "Profile") } },
                actions = {
                    IconButton(onClick = { scope.launch { drawer.open() } }) { Icon(Icons.Default.Menu, "Menu") }
                }
            )
            content()
        }
    }
}

@Composable
fun StatRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label)
        Text(value)
    }
}
