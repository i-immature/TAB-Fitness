package com.tab.fitness.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tab.fitness.data.model.MealEntity
import com.tab.fitness.data.model.ProfileEntity
import com.tab.fitness.data.model.TodoEntity
import com.tab.fitness.data.model.WorkoutEntity
import com.tab.fitness.data.repo.TabRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

data class DashboardStats(
    val caloriesBurned: Int = 0,
    val protein: Int = 0,
    val carbs: Int = 0,
    val todoCompleted: Int = 0,
    val todoTotal: Int = 0,
    val sleepHours: Float = 0f,
    val scoreOutOf10: Int = 0
)

class TabViewModel(private val repository: TabRepository) : ViewModel() {
    val profile = repository.profile.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
    val todos = repository.todos.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val workouts = repository.workouts.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val meals = repository.meals.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val watchSync = repository.watchSync.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    val dashboard: StateFlow<DashboardStats> = combine(todos, workouts, meals, watchSync) { todo, workout, meal, watch ->
        val done = todo.count { it.done }
        val score = ((done * 4f) + (workout.sumOf { it.caloriesBurned } / 100f) + (meal.sumOf { it.protein } / 30f) + ((watch?.sleepHours ?: 0f) / 2f))
            .roundToInt().coerceIn(0, 10)
        DashboardStats(
            caloriesBurned = workout.sumOf { it.caloriesBurned },
            protein = meal.sumOf { it.protein },
            carbs = meal.sumOf { it.carbs },
            todoCompleted = done,
            todoTotal = todo.size,
            sleepHours = watch?.sleepHours ?: 0f,
            scoreOutOf10 = score
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), DashboardStats())

    fun saveProfile(profileEntity: ProfileEntity) = viewModelScope.launch { repository.saveProfile(profileEntity) }
    fun addTodo(title: String, category: String) = viewModelScope.launch { repository.addTodo(TodoEntity(title = title, category = category)) }
    fun toggleTodo(id: Int, done: Boolean) = viewModelScope.launch { repository.setTodoDone(id, done) }
    fun addWorkout(exercise: String, calories: Int) = viewModelScope.launch { repository.addWorkout(WorkoutEntity(exercise = exercise, caloriesBurned = calories)) }
    fun addMeal(name: String, protein: Int, carbs: Int, fat: Int, calories: Int) =
        viewModelScope.launch { repository.addMeal(MealEntity(name = name, protein = protein, carbs = carbs, fat = fat, calories = calories)) }
    fun syncWatch(hours: Float) = viewModelScope.launch { repository.syncWatch(hours) }
}

class TabViewModelFactory(private val repository: TabRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = TabViewModel(repository) as T
}
