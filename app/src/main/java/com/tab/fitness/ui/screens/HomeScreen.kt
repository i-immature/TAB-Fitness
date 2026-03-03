package com.tab.fitness.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tab.fitness.viewmodel.TabViewModel

@Composable
fun HomeScreen(vm: TabViewModel) {
    val stats by vm.dashboard.collectAsState()
    TabTopBar {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Card { Column(Modifier.padding(12.dp)) {
                Text("Dashboard")
                StatRow("Workout Calories", stats.caloriesBurned.toString())
                StatRow("Protein / Carbs", "${stats.protein}g / ${stats.carbs}g")
                StatRow("To-Do Completion", "${stats.todoCompleted}/${stats.todoTotal}")
                StatRow("Sleep", "${stats.sleepHours} h")
                StatRow("Day Score", "${stats.scoreOutOf10}/10")
            } }
            FloatingActionButton(onClick = {}) { Text("🤖") }
        }
    }
}
