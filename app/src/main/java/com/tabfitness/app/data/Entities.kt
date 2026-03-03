package com.tabfitness.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val caloriesBurned: Int,
    val reps: Int?,
    val sets: Int?,
    val weightKg: Float?,
    val durationMin: Int?,
    val distanceKm: Float?,
    val dateEpoch: Long = System.currentTimeMillis()
)

@Entity
data class MealEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val proteinG: Int,
    val carbsG: Int,
    val fatG: Int,
    val calories: Int,
    val dateEpoch: Long = System.currentTimeMillis()
)

@Entity
data class TodoEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val category: String,
    val reminder: String,
    val done: Boolean = false,
    val createdEpoch: Long = System.currentTimeMillis()
)

@Entity
data class SleepSyncEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val source: String,
    val hours: Float,
    val syncedAtEpoch: Long = System.currentTimeMillis()
)

@Entity
data class Profile(
    @PrimaryKey val id: Int = 1,
    val username: String,
    val fullName: String,
    val email: String,
    val heightCm: Float,
    val weightKg: Float,
    val photoUri: String?,
    val goal: String
)
