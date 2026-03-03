package com.tab.fitness.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

@Composable
fun ChatBotSheet(onClose: () -> Unit) {
    val prompt = remember { mutableStateOf("Create my 6-day workout + meals") }
    val response = remember { mutableStateOf("Fitness Trainer AI: Ask anything about gym, yoga, meal plans, weight gain/loss, and reminders.") }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("Fitness Trainer AI")
        OutlinedTextField(value = prompt.value, onValueChange = { prompt.value = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Ask TAB AI") })
        Button(onClick = {
            response.value = "For '${prompt.value}', train 6 days + 1 rest day. I'll schedule push/pull/legs, add yoga mobility, and meal reminders for breakfast/lunch/dinner/pre-post workout."
        }) { Text("Send") }
        Text(response.value)
        Button(onClick = onClose) { Text("Close") }
    }
}
