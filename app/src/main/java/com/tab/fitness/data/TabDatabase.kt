package com.tab.fitness.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProfileEntity::class, TodoEntity::class, WorkoutEntity::class, MealEntity::class, SleepEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TabDatabase : RoomDatabase() {
    abstract fun dao(): TabDao
}
