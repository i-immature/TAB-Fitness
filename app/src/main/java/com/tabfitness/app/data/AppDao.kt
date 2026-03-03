package com.tabfitness.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProfile(profile: Profile)

    @Query("SELECT * FROM Profile WHERE id = 1")
    fun observeProfile(): Flow<Profile?>

    @Insert
    suspend fun addWorkout(entry: WorkoutEntry)

    @Query("SELECT * FROM WorkoutEntry ORDER BY dateEpoch DESC")
    fun observeWorkouts(): Flow<List<WorkoutEntry>>

    @Insert
    suspend fun addMeal(entry: MealEntry)

    @Query("SELECT * FROM MealEntry ORDER BY dateEpoch DESC")
    fun observeMeals(): Flow<List<MealEntry>>

    @Insert
    suspend fun addTodo(entry: TodoEntry)

    @Update
    suspend fun updateTodo(entry: TodoEntry)

    @Query("SELECT * FROM TodoEntry ORDER BY createdEpoch DESC")
    fun observeTodos(): Flow<List<TodoEntry>>

    @Insert
    suspend fun addSleepSync(entry: SleepSyncEntry)

    @Query("SELECT * FROM SleepSyncEntry ORDER BY syncedAtEpoch DESC")
    fun observeSleep(): Flow<List<SleepSyncEntry>>
}
