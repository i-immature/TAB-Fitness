package com.tabfitness.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tabfitness.app.auth.AuthManager
import com.tabfitness.app.data.AppDatabase
import com.tabfitness.app.data.AppRepository
import com.tabfitness.app.data.MealEntry
import com.tabfitness.app.data.Profile
import com.tabfitness.app.data.TodoEntry
import com.tabfitness.app.data.WorkoutEntry
import com.tabfitness.app.ui.collectAsStateCompat
import com.tabfitness.app.ui.theme.AppSkin
import com.tabfitness.app.ui.theme.TabTheme
import com.tabfitness.app.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authManager = AuthManager(this)
        val repository = AppRepository(AppDatabase.get(this).dao())
        setContent {
            val vm: MainViewModel = viewModel(factory = MainViewModel.Factory(repository))
            TabTheme(skin = vm.selectedSkin, darkMode = vm.darkMode) {
                TabApp(vm, authManager)
            }
        }
    }
}

enum class TabScreen { HOME, TODO, WORKOUT, MEALS }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabApp(vm: MainViewModel, auth: AuthManager) {
    var showSplash by remember { mutableStateOf(true) }
    var loggedIn by remember { mutableStateOf(auth.isLoggedIn()) }
    var firstSetupDone by remember { mutableStateOf(false) }
    var tab by remember { mutableStateOf(TabScreen.HOME) }
    var showBot by remember { mutableStateOf(false) }
    var showProfileDialog by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(1600)
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
        return
    }

    if (!loggedIn) {
        AuthScreen(onAuth = { email, pass ->
            loggedIn = auth.signupOrLogin(email, pass)
        })
        return
    }

    val profile by vm.profile.collectAsStateCompat()
    if (!firstSetupDone && profile == null) {
        ProfileSetupScreen {
            vm.saveProfile(it)
            firstSetupDone = true
        }
        return
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                vm = vm,
                onLogout = {
                    auth.logout()
                    loggedIn = false
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("TAB", fontWeight = FontWeight.Bold, fontSize = 24.sp) },
                    navigationIcon = {
                        IconButton(onClick = { showProfileDialog = true }) {
                            Icon(Icons.Default.Person, contentDescription = "Profile")
                        }
                    },
                    actions = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(selected = tab == TabScreen.HOME, onClick = { tab = TabScreen.HOME }, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home") })
                    NavigationBarItem(selected = tab == TabScreen.TODO, onClick = { tab = TabScreen.TODO }, icon = { Icon(Icons.Default.Task, null) }, label = { Text("To-Do") })
                    NavigationBarItem(selected = tab == TabScreen.WORKOUT, onClick = { tab = TabScreen.WORKOUT }, icon = { Icon(Icons.Default.Watch, null) }, label = { Text("Workouts") })
                    NavigationBarItem(selected = tab == TabScreen.MEALS, onClick = { tab = TabScreen.MEALS }, icon = { Icon(Icons.Default.Restaurant, null) }, label = { Text("Meals") })
                }
            },
            floatingActionButton = {
                Column(horizontalAlignment = Alignment.End) {
                    FloatingActionButton(onClick = { showBot = true }, modifier = Modifier.padding(bottom = 8.dp)) { Text("🤖") }
                    if (tab != TabScreen.HOME) {
                        FloatingActionButton(onClick = { showAddDialog = true }) { Icon(Icons.Default.Add, null) }
                    }
                }
            }
        ) { pad ->
            Box(Modifier.fillMaxSize().padding(pad)) {
                when (tab) {
                    TabScreen.HOME -> HomeScreen(vm)
                    TabScreen.TODO -> TodoScreen(vm)
                    TabScreen.WORKOUT -> WorkoutScreen(vm)
                    TabScreen.MEALS -> MealsScreen(vm)
                }
            }
        }
    }

    if (showProfileDialog) ProfileDialog(profile = vm.profile.collectAsStateCompat().value, onDismiss = { showProfileDialog = false })
    if (showBot) BotDialog(onDismiss = { showBot = false })
    if (showAddDialog) AddDialog(tab, vm, onDismiss = { showAddDialog = false })
}

@Composable
fun SplashScreen() {
    Box(
        Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text("TAB The Absolute Body", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun AuthScreen(onAuth: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
        Text("Welcome to TAB", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(email, { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(password, { password = it }, label = { Text("Password (6+)") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        Button(onClick = { onAuth(email, password) }, modifier = Modifier.fillMaxWidth()) { Text("Sign up / Login") }
    }
}

@Composable
fun ProfileSetupScreen(onDone: (Profile) -> Unit) {
    var username by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("170") }
    var weight by remember { mutableStateOf("70") }
    var goal by remember { mutableStateOf("Build physique") }
    LazyColumn(Modifier.fillMaxSize().padding(24.dp)) {
        item { Text("First-Time Setup", style = MaterialTheme.typography.headlineSmall) }
        item { OutlinedTextField(username, { username = it }, label = { Text("Username") }) }
        item { OutlinedTextField(name, { name = it }, label = { Text("Name") }) }
        item { OutlinedTextField(email, { email = it }, label = { Text("Email") }) }
        item { OutlinedTextField(height, { height = it }, label = { Text("Height cm") }) }
        item { OutlinedTextField(weight, { weight = it }, label = { Text("Weight kg") }) }
        item { OutlinedTextField(goal, { goal = it }, label = { Text("Priority Goal") }) }
        item {
            Spacer(Modifier.height(12.dp))
            Button(onClick = {
                onDone(
                    Profile(
                        username = username,
                        fullName = name,
                        email = email,
                        heightCm = height.toFloatOrNull() ?: 0f,
                        weightKg = weight.toFloatOrNull() ?: 0f,
                        photoUri = null,
                        goal = goal
                    )
                )
            }, modifier = Modifier.fillMaxWidth()) { Text("Continue") }
        }
    }
}

@Composable
fun DrawerContent(vm: MainViewModel, onLogout: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Menu", style = MaterialTheme.typography.headlineSmall)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dark Mode")
            Spacer(Modifier.weight(1f))
            Switch(checked = vm.darkMode, onCheckedChange = { vm.darkMode = it })
        }
        Text("Add Watch & Sync", modifier = Modifier.clickable { vm.syncWatch(7.5f) })
        Text("Settings: App theme + ringtone/reminder control")
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { vm.selectedSkin = AppSkin.LIQUID_GLASS }) { Text("Liquid") }
            Button(onClick = { vm.selectedSkin = AppSkin.MATERIAL }) { Text("Material") }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { vm.selectedSkin = AppSkin.ONE_UI }) { Text("One UI") }
            Button(onClick = { vm.selectedSkin = AppSkin.NOTHING }) { Text("Nothing") }
        }
        Text("Ringtone/Reminder: Default alarm tone")
        Text("Open Linktree: https://linktr.ee/immature.ig")
        Spacer(Modifier.weight(1f))
        Text("Version 1.0.0", modifier = Modifier.align(Alignment.CenterHorizontally))
        Button(onClick = onLogout, modifier = Modifier.align(Alignment.CenterHorizontally)) { Text("Logout") }
    }
}

@Composable
fun HomeScreen(vm: MainViewModel) {
    val summary by vm.summary.collectAsStateCompat()
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Dashboard", style = MaterialTheme.typography.headlineSmall)
        CardTile("Workout calories", "${summary.caloriesBurned} kcal")
        CardTile("Meals macros", "P ${summary.protein}g / C ${summary.carbs}g / F ${summary.fat}g")
        CardTile("To-Do completion", "${summary.tasksDone}/${summary.totalTasks}")
        CardTile("Sleep (from watch)", "${summary.sleepHours} hrs")
        CardTile("Daily score", "${summary.dayScore}/10")
    }
}

@Composable
fun WorkoutScreen(vm: MainViewModel) {
    val items by vm.workouts.collectAsStateCompat()
    ListScreen(title = "Workouts", lines = items.map { "${it.name} • ${it.caloriesBurned} kcal" })
}

@Composable
fun MealsScreen(vm: MainViewModel) {
    val items by vm.meals.collectAsStateCompat()
    ListScreen(title = "Meals", lines = items.map { "${it.name} • ${it.proteinG}P/${it.carbsG}C/${it.fatG}F" })
}

@Composable
fun TodoScreen(vm: MainViewModel) {
    val items by vm.todos.collectAsStateCompat()
    LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item { Text("To-Do", style = MaterialTheme.typography.headlineSmall) }
        items(items) { todo ->
            Card(
                modifier = Modifier.fillMaxWidth().clickable { vm.toggleTodo(todo) },
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(if (todo.done) "☑" else "☐", fontSize = 22.sp)
                    Spacer(Modifier.size(8.dp))
                    Column { Text(todo.title); Text(todo.category, style = MaterialTheme.typography.bodySmall) }
                }
            }
        }
    }
}

@Composable
fun ListScreen(title: String, lines: List<String>) {
    LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        item { Text(title, style = MaterialTheme.typography.headlineSmall) }
        items(lines) { line -> CardTile("", line) }
    }
}

@Composable
fun CardTile(label: String, value: String) {
    Card(shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(14.dp)) {
            if (label.isNotBlank()) Text(label, style = MaterialTheme.typography.labelLarge)
            Text(value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun AddDialog(tab: TabScreen, vm: MainViewModel, onDismiss: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var a by remember { mutableStateOf("0") }
    var b by remember { mutableStateOf("0") }
    var c by remember { mutableStateOf("0") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add entry") },
        text = {
            Column {
                OutlinedTextField(name, { name = it }, label = { Text("Name / Title") })
                OutlinedTextField(a, { a = it }, label = { Text("Value A") })
                OutlinedTextField(b, { b = it }, label = { Text("Value B") })
                OutlinedTextField(c, { c = it }, label = { Text("Value C") })
            }
        },
        confirmButton = {
            TextButton(onClick = {
                when (tab) {
                    TabScreen.WORKOUT -> vm.addWorkout(WorkoutEntry(name = name, caloriesBurned = a.toIntOrNull() ?: 0, reps = b.toIntOrNull(), sets = c.toIntOrNull(), weightKg = null, durationMin = null, distanceKm = null))
                    TabScreen.MEALS -> vm.addMeal(MealEntry(name = name, proteinG = a.toIntOrNull() ?: 0, carbsG = b.toIntOrNull() ?: 0, fatG = c.toIntOrNull() ?: 0, calories = 0))
                    TabScreen.TODO -> vm.addTodo(TodoEntry(title = name, category = b.ifBlank { "General" }, reminder = c.ifBlank { "Daily" }))
                    TabScreen.HOME -> Unit
                }
                onDismiss()
            }) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}

@Composable
fun BotDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Fitness Trainer AI") },
        text = {
            Text("Hi! I'm your humble TAB trainer. Share your goal (gain, lose, or physique), workout days/week, and I will suggest a workout + meal rhythm with reminders.")
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Got it") } }
    )
}

@Composable
fun ProfileDialog(profile: Profile?, onDismiss: () -> Unit) {
    val bmi = if (profile == null || profile.heightCm <= 0f) 0f else profile.weightKg / ((profile.heightCm / 100f) * (profile.heightCm / 100f))
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Profile") },
        text = {
            Column {
                Text("Username: ${profile?.username ?: "-"}")
                Text("Name: ${profile?.fullName ?: "-"}")
                Text("Email: ${profile?.email ?: "-"}")
                Text("Height: ${profile?.heightCm ?: 0f} cm")
                Text("Weight: ${profile?.weightKg ?: 0f} kg")
                Text("BMI: ${"%.1f".format(bmi)}")
                Text("Scale: ${bmiScale(bmi)}")
                Text("Photo: adaptable placeholder")
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Close") } }
    )
}

fun bmiScale(bmi: Float): String = when {
    bmi == 0f -> "Not set"
    bmi < 18.5 -> "Underweight"
    bmi < 24.9 -> "Normal"
    bmi < 29.9 -> "Overweight"
    else -> "Obese"
}
