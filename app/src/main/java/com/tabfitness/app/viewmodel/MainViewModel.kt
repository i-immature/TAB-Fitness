package com.tabfitness.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tabfitness.app.data.AppRepository
import com.tabfitness.app.data.MealEntry
import com.tabfitness.app.data.Profile
import com.tabfitness.app.data.TodoEntry
import com.tabfitness.app.data.WorkoutEntry
import com.tabfitness.app.ui.theme.AppSkin
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class DashboardSummary(
    val caloriesBurned: Int = 0,
    val protein: Int = 0,
    val carbs: Int = 0,
    val fat: Int = 0,
    val tasksDone: Int = 0,
    val totalTasks: Int = 0,
    val sleepHours: Float = 0f,
    val dayScore: Int = 0
)

class MainViewModel(private val repository: AppRepository) : ViewModel() {
    val profile = repository.profile.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
    val workouts = repository.workouts.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val meals = repository.meals.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val todos = repository.todos.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val sleep = repository.sleep.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    var selectedSkin by mutableStateOf(AppSkin.LIQUID_GLASS)
    var darkMode by mutableStateOf(true)

    val summary: StateFlow<DashboardSummary> = combine(workouts, meals, todos, sleep) { w, m, t, s ->
        val burned = w.sumOf { it.caloriesBurned }
        val protein = m.sumOf { it.proteinG }
        val carbs = m.sumOf { it.carbsG }
        val fat = m.sumOf { it.fatG }
        val done = t.count { it.done }
        val total = t.size
        val sleepH = s.firstOrNull()?.hours ?: 0f
        val score = ((burned / 60) + (protein / 20) + (if (total == 0) 0 else (done * 5 / total)) + sleepH.toInt()).coerceIn(0, 10)
        DashboardSummary(burned, protein, carbs, fat, done, total, sleepH, score)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), DashboardSummary())

    fun saveProfile(profile: Profile) = viewModelScope.launch { repository.saveProfile(profile) }
    fun addWorkout(entry: WorkoutEntry) = viewModelScope.launch { repository.addWorkout(entry) }
    fun addMeal(entry: MealEntry) = viewModelScope.launch { repository.addMeal(entry) }
    fun addTodo(entry: TodoEntry) = viewModelScope.launch { repository.addTodo(entry) }
    fun toggleTodo(entry: TodoEntry) = viewModelScope.launch { repository.toggleTodo(entry) }
    fun syncWatch(hours: Float) = viewModelScope.launch { repository.syncSleep("Watch", hours) }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: AppRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(repository) as T
    }
}
