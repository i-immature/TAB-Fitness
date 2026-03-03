package com.tab.fitness.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Card(modifier = Modifier.fillMaxWidth()) { Text("Dashboard: Today / Weekly / Monthly", modifier = Modifier.padding(12.dp)) }
        Card(modifier = Modifier.fillMaxWidth()) { Text("Workouts: 420 kcal burned", modifier = Modifier.padding(12.dp)) }
        Card(modifier = Modifier.fillMaxWidth()) { Text("Meals: Protein 120g, Carbs 210g, Fat 60g", modifier = Modifier.padding(12.dp)) }
        Card(modifier = Modifier.fillMaxWidth()) { Text("To-Do: 7/9 tasks completed on time", modifier = Modifier.padding(12.dp)) }
        Card(modifier = Modifier.fillMaxWidth()) { Text("Sleep: 7.5h average", modifier = Modifier.padding(12.dp)) }
        Card(modifier = Modifier.fillMaxWidth()) { Text("Overall score: 8.4 / 10", modifier = Modifier.padding(12.dp)) }
    }
}
