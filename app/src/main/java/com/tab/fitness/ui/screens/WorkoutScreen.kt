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
fun WorkoutScreen(vm: TabViewModel) {
    val workouts by vm.workouts.collectAsState()
    TabTopBar {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            FloatingActionButton(onClick = { vm.addWorkout("Push Ups", 40) }) { Text("+") }
            FloatingActionButton(onClick = {}) { Text("🤖") }
            LazyColumn {
                items(workouts) { w ->
                    ListItem(headlineContent = { Text(w.exercise) }, supportingContent = { Text("${w.caloriesBurned} kcal") })
                }
            }
        }
    }
}
