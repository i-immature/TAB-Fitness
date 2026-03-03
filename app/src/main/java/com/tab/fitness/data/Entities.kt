package com.tab.fitness.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileEntity(
    @PrimaryKey val id: Int = 1,
    val username: String = "",
    val name: String = "",
    val email: String = "",
    val photoUri: String = "",
    val heightCm: Int = 170,
    val weightKg: Float = 70f,
    val goal: String = "Build Physique"
)

@Entity
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val category: String,
    val reminder: String,
    val completed: Boolean = false
)

@Entity
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val calories: Int,
    val reps: Int,
    val sets: Int,
    val distanceKm: Float,
    val durationMin: Int
)

@Entity
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val protein: Int,
    val carbs: Int,
    val fats: Int,
    val calories: Int
)

@Entity
data class SleepEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val hours: Float,
    val quality: Int
)
