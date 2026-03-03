package com.tab.fitness.data

import android.content.Context
import androidx.room.Room
import com.tab.fitness.auth.AuthRepository
import com.tab.fitness.chat.ChatRepository

class AppContainer(context: Context) {
    private val db = Room.databaseBuilder(context, TabDatabase::class.java, "tab.db").build()
    val dao = db.dao()
    val authRepository = AuthRepository(context)
    val chatRepository = ChatRepository()
}
