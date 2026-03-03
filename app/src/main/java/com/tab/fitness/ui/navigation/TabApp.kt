package com.tab.fitness.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.tab.fitness.data.local.TabDatabase
import com.tab.fitness.data.repo.TabRepository
import com.tab.fitness.ui.screens.HomeScreen
import com.tab.fitness.ui.screens.MealsScreen
import com.tab.fitness.ui.screens.OnboardingScreen
import com.tab.fitness.ui.screens.TodoScreen
import com.tab.fitness.ui.screens.WorkoutScreen
import com.tab.fitness.viewmodel.TabViewModel
import com.tab.fitness.viewmodel.TabViewModelFactory

enum class TabDestination(val route: String, val label: String) {
    Home("home", "Home"), Todo("todo", "To-Do"), Workouts("workouts", "Workouts"), Meals("meals", "Meals")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabApp() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val db = remember {
        Room.databaseBuilder(context, TabDatabase::class.java, "tab.db").build()
    }
    val vm: TabViewModel = viewModel(factory = TabViewModelFactory(TabRepository(db.dao())))
    val navController = rememberNavController()
    var firstLaunch by remember { mutableStateOf(true) }

    if (firstLaunch) {
        OnboardingScreen(onDone = { firstLaunch = false }, vm = vm)
        return
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = TabDestination.entries
                val backStack by navController.currentBackStackEntryAsState()
                items.forEach { dest ->
                    NavigationBarItem(
                        selected = backStack?.destination?.hierarchy?.any { it.route == dest.route } == true,
                        onClick = { navController.navigate(dest.route) },
                        icon = {
                            Icon(
                                imageVector = when (dest) {
                                    TabDestination.Home -> Icons.Default.Home
                                    TabDestination.Todo -> Icons.Default.List
                                    TabDestination.Workouts -> Icons.Default.FitnessCenter
                                    TabDestination.Meals -> Icons.Default.Dining
                                },
                                contentDescription = dest.label
                            )
                        },
                        label = { Text(dest.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(navController = navController, startDestination = TabDestination.Home.route, modifier = Modifier.padding(padding)) {
            composable(TabDestination.Home.route) { HomeScreen(vm) }
            composable(TabDestination.Todo.route) { TodoScreen(vm) }
            composable(TabDestination.Workouts.route) { WorkoutScreen(vm) }
            composable(TabDestination.Meals.route) { MealsScreen(vm) }
        }
    }
}
