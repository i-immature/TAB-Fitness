package com.tab.fitness.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorkoutsScreen() {
    val workout = remember { mutableStateOf("Bench Press") }
    val sets = remember { mutableStateOf("4") }
    val reps = remember { mutableStateOf("10") }
    val timer = remember { mutableStateOf("45") }
    val data = remember { mutableStateListOf("Push-up • 3x15 • 50 kcal") }

    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Workout")
        OutlinedTextField(value = workout.value, onValueChange = { workout.value = it }, label = { Text("Exercise") }, modifier = Modifier.fillMaxWidth())
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(value = sets.value, onValueChange = { sets.value = it }, label = { Text("Sets") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = reps.value, onValueChange = { reps.value = it }, label = { Text("Reps") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = timer.value, onValueChange = { timer.value = it }, label = { Text("Timer") }, modifier = Modifier.weight(1f))
        }
        Button(onClick = { data.add("${workout.value} • ${sets.value}x${reps.value} • ${timer.value} min") }) { Text("+") }
        data.forEach { Text("• $it") }
    }
}
