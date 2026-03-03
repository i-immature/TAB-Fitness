package com.tab.fitness.chat

import kotlinx.coroutines.delay

class ChatRepository {
    suspend fun ask(prompt: String): String {
        delay(500)
        return "Coach TAB 🤖: Great focus. Based on your goal, I suggest progressive overload, hydration, and 7+ hours of sleep. For '$prompt', I can build a weekly workout and meal schedule next."
    }
}
