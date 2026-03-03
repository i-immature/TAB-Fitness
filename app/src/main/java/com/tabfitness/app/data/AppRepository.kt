package com.tabfitness.app.data

class AppRepository(private val dao: AppDao) {
    val profile = dao.observeProfile()
    val workouts = dao.observeWorkouts()
    val meals = dao.observeMeals()
    val todos = dao.observeTodos()
    val sleep = dao.observeSleep()

    suspend fun saveProfile(profile: Profile) = dao.upsertProfile(profile)
    suspend fun addWorkout(entry: WorkoutEntry) = dao.addWorkout(entry)
    suspend fun addMeal(entry: MealEntry) = dao.addMeal(entry)
    suspend fun addTodo(entry: TodoEntry) = dao.addTodo(entry)
    suspend fun toggleTodo(entry: TodoEntry) = dao.updateTodo(entry.copy(done = !entry.done))
    suspend fun syncSleep(source: String, hours: Float) = dao.addSleepSync(SleepSyncEntry(source = source, hours = hours))
}
