package com.tab.fitness.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class TaskItem(val title: String, val category: String, var done: Boolean)

@Composable
fun TodoScreen() {
    val title = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("General") }
    val tasks = remember {
        mutableStateListOf(
            TaskItem("Morning Walk", "Health", false),
            TaskItem("Protein shake", "Meal", true)
        )
    }

    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("To-Do")
        OutlinedTextField(value = title.value, onValueChange = { title.value = it }, label = { Text("Task") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = category.value, onValueChange = { category.value = it }, label = { Text("Category") }, modifier = Modifier.fillMaxWidth())
        Button(onClick = {
            if (title.value.isNotBlank()) tasks.add(TaskItem(title.value, category.value, false))
            title.value = ""
        }) { Text("+") }
        tasks.forEachIndexed { index, item ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("${item.title} • ${item.category}")
                Checkbox(checked = item.done, onCheckedChange = { tasks[index] = item.copy(done = it) })
            }
        }
    }
}
