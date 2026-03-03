package com.tab.fitness.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tab.fitness.data.model.MealEntity
import com.tab.fitness.data.model.ProfileEntity
import com.tab.fitness.data.model.TodoEntity
import com.tab.fitness.data.model.WatchSyncEntity
import com.tab.fitness.data.model.WorkoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TabDao {
    @Query("SELECT * FROM profile WHERE id = 1")
    fun profile(): Flow<ProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProfile(profile: ProfileEntity)

    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun todos(): Flow<List<TodoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity)

    @Query("UPDATE todo SET done = :done WHERE id = :id")
    suspend fun setTodoDone(id: Int, done: Boolean)

    @Query("SELECT * FROM workout ORDER BY createdAt DESC")
    fun workouts(): Flow<List<WorkoutEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity)

    @Query("SELECT * FROM meal ORDER BY createdAt DESC")
    fun meals(): Flow<List<MealEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity)

    @Query("SELECT * FROM watch_sync WHERE id=1")
    fun watchSync(): Flow<WatchSyncEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertWatchSync(sync: WatchSyncEntity)
}
