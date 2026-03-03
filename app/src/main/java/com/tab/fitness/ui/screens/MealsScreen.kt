package com.tab.fitness.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tab.fitness.viewmodel.TabViewModel

@Composable
fun MealsScreen(vm: TabViewModel) {
    val meals by vm.meals.collectAsState()
    TabTopBar {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            FloatingActionButton(onClick = { vm.addMeal("Chicken Bowl", 45, 35, 12, 460) }) { Text("+") }
            FloatingActionButton(onClick = {}) { Text("🤖") }
            LazyColumn {
                items(meals) { m ->
                    ListItem(
                        headlineContent = { Text(m.name) },
                        supportingContent = { Text("P ${m.protein}g / C ${m.carbs}g / ${m.calories} kcal") }
                    )
                }
            }
        }
    }
}
