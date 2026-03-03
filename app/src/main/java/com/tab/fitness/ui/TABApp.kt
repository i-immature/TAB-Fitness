package com.tab.fitness.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tab.fitness.components.ChatBotSheet
import com.tab.fitness.components.HamburgerDrawer
import com.tab.fitness.components.LoginGate
import com.tab.fitness.components.ProfileSheet
import com.tab.fitness.ui.screens.HomeScreen
import com.tab.fitness.ui.screens.MealsScreen
import com.tab.fitness.ui.screens.TodoScreen
import com.tab.fitness.ui.screens.WorkoutsScreen
import com.tab.fitness.ui.theme.AppSkin
import com.tab.fitness.ui.theme.TABTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private enum class Tab { Home, Todo, Workouts, Meals }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TABApp() {
    var showSplash by remember { mutableStateOf(true) }
    var darkMode by remember { mutableStateOf(true) }
    var skin by remember { mutableStateOf(AppSkin.LiquidGlass) }
    var currentTab by remember { mutableStateOf(Tab.Home) }
    var showProfile by remember { mutableStateOf(false) }
    var showChat by remember { mutableStateOf(false) }
    var loggedIn by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1600)
        showSplash = false
    }

    TABTheme(skin = skin, darkMode = darkMode) {
        if (showSplash) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (darkMode) androidx.compose.ui.graphics.Color.Black else androidx.compose.ui.graphics.Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "TAB The Absolute Body",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (darkMode) androidx.compose.ui.graphics.Color.White else androidx.compose.ui.graphics.Color.Black
                )
            }
            return@TABTheme
        }

        if (!loggedIn) {
            LoginGate(onLoginDone = { loggedIn = true })
            return@TABTheme
        }

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                HamburgerDrawer(
                    darkMode = darkMode,
                    onDarkModeChanged = { darkMode = it },
                    selectedSkin = skin,
                    onSkinSelect = { skin = it },
                    onLogout = { loggedIn = false }
                )
            }
        ) {
            Scaffold(
                topBar = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { showProfile = true }) { Icon(Icons.Default.Person, contentDescription = "Profile") }
                        Text("TAB", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        IconButton(onClick = { scope.launch { drawerState.open() } }) { Icon(Icons.Default.Menu, contentDescription = "Menu") }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { showChat = true }) { Text("🤖") }
                },
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(selected = currentTab == Tab.Home, onClick = { currentTab = Tab.Home }, label = { Text("Home") }, icon = { Text("🏠") })
                        NavigationBarItem(selected = currentTab == Tab.Todo, onClick = { currentTab = Tab.Todo }, label = { Text("To-Do") }, icon = { Text("✅") })
                        NavigationBarItem(selected = currentTab == Tab.Workouts, onClick = { currentTab = Tab.Workouts }, label = { Text("Workouts") }, icon = { Text("🏋️") })
                        NavigationBarItem(selected = currentTab == Tab.Meals, onClick = { currentTab = Tab.Meals }, label = { Text("Meals") }, icon = { Text("🥗") })
                    }
                }
            ) { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    when (currentTab) {
                        Tab.Home -> HomeScreen()
                        Tab.Todo -> TodoScreen()
                        Tab.Workouts -> WorkoutsScreen()
                        Tab.Meals -> MealsScreen()
                    }
                }
            }
        }

        if (showProfile) ProfileSheet(onClose = { showProfile = false })
        if (showChat) ChatBotSheet(onClose = { showChat = false })
    }
}
