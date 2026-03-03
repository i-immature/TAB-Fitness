package com.tab.fitness.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tab.fitness.data.model.MealEntity
import com.tab.fitness.data.model.ProfileEntity
import com.tab.fitness.data.model.TodoEntity
import com.tab.fitness.data.model.WatchSyncEntity
import com.tab.fitness.data.model.WorkoutEntity

@Database(
    entities = [ProfileEntity::class, TodoEntity::class, WorkoutEntity::class, MealEntity::class, WatchSyncEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TabDatabase : RoomDatabase() {
    abstract fun dao(): TabDao
}
