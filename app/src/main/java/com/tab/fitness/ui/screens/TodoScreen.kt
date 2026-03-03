package com.tab.fitness.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tab.fitness.viewmodel.TabViewModel

@Composable
fun TodoScreen(vm: TabViewModel) {
    val todos by vm.todos.collectAsState()
    val task = remember { mutableStateOf("") }
    TabTopBar {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            OutlinedTextField(task.value, { task.value = it }, label = { Text("Add task with category") })
            FloatingActionButton(onClick = { vm.addTodo(task.value, "General"); task.value = "" }) { Text("+") }
            LazyColumn {
                items(todos) { todo ->
                    ListItem(headlineContent = { Text(todo.title) }, supportingContent = { Text(todo.category) }, leadingContent = {
                        Checkbox(checked = todo.done, onCheckedChange = { vm.toggleTodo(todo.id, it) })
                    })
                }
            }
        }
    }
}
