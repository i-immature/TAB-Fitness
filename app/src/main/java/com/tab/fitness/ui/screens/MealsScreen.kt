package com.tab.fitness.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun MealsScreen() {
    val meal = remember { mutableStateOf("Chicken & Rice") }
    val protein = remember { mutableStateOf("35") }
    val carbs = remember { mutableStateOf("42") }
    val fats = remember { mutableStateOf("9") }
    val entries = remember { mutableStateListOf("Egg Whites • P24 C2 F1") }

    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Meals")
        OutlinedTextField(value = meal.value, onValueChange = { meal.value = it }, label = { Text("Meal") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = protein.value, onValueChange = { protein.value = it }, label = { Text("Protein") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = carbs.value, onValueChange = { carbs.value = it }, label = { Text("Carbs") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = fats.value, onValueChange = { fats.value = it }, label = { Text("Fats") }, modifier = Modifier.fillMaxWidth())
        Button(onClick = { entries.add("${meal.value} • P${protein.value} C${carbs.value} F${fats.value}") }) { Text("+") }
        entries.forEach { Text("• $it") }
    }
}
