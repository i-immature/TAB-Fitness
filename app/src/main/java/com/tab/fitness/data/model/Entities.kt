package com.tab.fitness.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey val id: Int = 1,
    val username: String = "",
    val name: String = "",
    val email: String = "",
    val heightCm: Float = 0f,
    val weightKg: Float = 0f,
    val profilePictureUri: String = "",
    val goal: String = "Build Physique"
)

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val category: String,
    val done: Boolean = false,
    val reminderEpoch: Long? = null
)

@Entity(tableName = "workout")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val exercise: String,
    val sets: Int? = null,
    val reps: Int? = null,
    val weightKg: Float? = null,
    val timerSeconds: Int? = null,
    val distanceKm: Float? = null,
    val caloriesBurned: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "meal")
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
    val calories: Int,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "watch_sync")
data class WatchSyncEntity(
    @PrimaryKey val id: Int = 1,
    val lastSync: Long = 0L,
    val sleepHours: Float = 0f
)
