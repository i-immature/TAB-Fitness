package com.tab.fitness.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tab.fitness.data.model.ProfileEntity
import com.tab.fitness.viewmodel.TabViewModel

@Composable
fun OnboardingScreen(onDone: () -> Unit, vm: TabViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val height = remember { mutableStateOf("170") }
    val weight = remember { mutableStateOf("70") }
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("TAB The Absolute Body", color = Color.White)
        OutlinedTextField(email.value, { email.value = it }, label = { Text("Email") })
        OutlinedTextField(password.value, { password.value = it }, label = { Text("Password") })
        OutlinedTextField(name.value, { name.value = it }, label = { Text("Name") })
        OutlinedTextField(height.value, { height.value = it }, label = { Text("Height (cm)") })
        OutlinedTextField(weight.value, { weight.value = it }, label = { Text("Weight (kg)") })
        Button(onClick = {
            vm.saveProfile(
                ProfileEntity(
                    username = email.value.substringBefore("@"),
                    name = name.value,
                    email = email.value,
                    heightCm = height.value.toFloatOrNull() ?: 0f,
                    weightKg = weight.value.toFloatOrNull() ?: 0f,
                    goal = "Build Physique"
                )
            )
            onDone()
        }) { Text("Login / Sign Up") }
    }
}
