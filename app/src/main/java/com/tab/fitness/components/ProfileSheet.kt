package com.tab.fitness.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.pow

@Composable
fun ProfileSheet(onClose: () -> Unit) {
    val username = remember { mutableStateOf("tab_user") }
    val name = remember { mutableStateOf("TAB Athlete") }
    val email = remember { mutableStateOf("") }
    val height = remember { mutableStateOf("170") }
    val weight = remember { mutableStateOf("70") }
    val bmi = runCatching {
        val h = height.value.toFloat() / 100f
        weight.value.toFloat() / h.pow(2)
    }.getOrElse { 0f }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Profile")
        Text("Picture: Adaptive avatar enabled")
        OutlinedTextField(value = username.value, onValueChange = { username.value = it }, label = { Text("Username") })
        OutlinedTextField(value = name.value, onValueChange = { name.value = it }, label = { Text("Name") })
        OutlinedTextField(value = email.value, onValueChange = { email.value = it }, label = { Text("Email") })
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(value = height.value, onValueChange = { height.value = it }, label = { Text("Height") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = weight.value, onValueChange = { weight.value = it }, label = { Text("Weight") }, modifier = Modifier.weight(1f))
        }
        Text("BMI meter: ${"%.1f".format(bmi)}")
        Text("BMI scale: <18.5 underweight | 18.5-24.9 fit | 25+ overweight")
        Button(onClick = onClose) { Text("Close") }
    }
}
