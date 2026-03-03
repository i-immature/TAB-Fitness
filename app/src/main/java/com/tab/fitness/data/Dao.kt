package com.tab.fitness.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TabDao {
    @Query("SELECT * FROM ProfileEntity LIMIT 1")
    fun profile(): Flow<ProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(profileEntity: ProfileEntity)

    @Query("SELECT * FROM TodoEntity")
    fun todos(): Flow<List<TodoEntity>>

    @Insert
    suspend fun addTodo(todo: TodoEntity)

    @Query("UPDATE TodoEntity SET completed = :completed WHERE id = :id")
    suspend fun setTodoComplete(id: Int, completed: Boolean)

    @Query("SELECT * FROM WorkoutEntity")
    fun workouts(): Flow<List<WorkoutEntity>>

    @Insert
    suspend fun addWorkout(workoutEntity: WorkoutEntity)

    @Query("SELECT * FROM MealEntity")
    fun meals(): Flow<List<MealEntity>>

    @Insert
    suspend fun addMeal(mealEntity: MealEntity)
}
