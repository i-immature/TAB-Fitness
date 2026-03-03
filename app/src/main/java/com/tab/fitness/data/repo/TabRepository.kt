package com.tab.fitness.data.repo

import com.tab.fitness.data.local.TabDao
import com.tab.fitness.data.model.MealEntity
import com.tab.fitness.data.model.ProfileEntity
import com.tab.fitness.data.model.TodoEntity
import com.tab.fitness.data.model.WatchSyncEntity
import com.tab.fitness.data.model.WorkoutEntity

class TabRepository(private val dao: TabDao) {
    val profile = dao.profile()
    val todos = dao.todos()
    val workouts = dao.workouts()
    val meals = dao.meals()
    val watchSync = dao.watchSync()

    suspend fun saveProfile(profileEntity: ProfileEntity) = dao.upsertProfile(profileEntity)
    suspend fun addTodo(todo: TodoEntity) = dao.insertTodo(todo)
    suspend fun setTodoDone(id: Int, done: Boolean) = dao.setTodoDone(id, done)
    suspend fun addWorkout(workout: WorkoutEntity) = dao.insertWorkout(workout)
    suspend fun addMeal(meal: MealEntity) = dao.insertMeal(meal)
    suspend fun syncWatch(sleepHours: Float) = dao.upsertWatchSync(WatchSyncEntity(lastSync = System.currentTimeMillis(), sleepHours = sleepHours))
}
