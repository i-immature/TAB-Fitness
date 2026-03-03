package com.tab.fitness.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginGate(onLoginDone: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val height = remember { mutableStateOf("170") }
    val weight = remember { mutableStateOf("70") }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to TAB")
        OutlinedTextField(value = email.value, onValueChange = { email.value = it }, label = { Text("Email") })
        OutlinedTextField(value = password.value, onValueChange = { password.value = it }, label = { Text("Password (JWT auth)") })
        OutlinedTextField(value = name.value, onValueChange = { name.value = it }, label = { Text("Name") })
        OutlinedTextField(value = height.value, onValueChange = { height.value = it }, label = { Text("Height cm") })
        OutlinedTextField(value = weight.value, onValueChange = { weight.value = it }, label = { Text("Weight kg") })
        Button(onClick = onLoginDone, modifier = Modifier.padding(top = 12.dp)) { Text("Login / Sign up") }
    }
}
