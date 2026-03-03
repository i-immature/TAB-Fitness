package com.tab.fitness.auth

import android.content.Context
import android.util.Base64
import kotlinx.coroutines.delay
import java.nio.charset.StandardCharsets
import java.util.Date

class AuthRepository(context: Context) {
    private val prefs = context.getSharedPreferences("tab-auth", Context.MODE_PRIVATE)

    suspend fun login(email: String, password: String): Boolean {
        delay(600)
        if (email.isBlank() || password.length < 6) return false
        prefs.edit().putString("jwt", createToken(email)).apply()
        return true
    }

    fun logout() {
        prefs.edit().remove("jwt").apply()
    }

    fun isLoggedIn(): Boolean = !prefs.getString("jwt", null).isNullOrBlank()

    private fun createToken(email: String): String {
        val header = Base64.encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".toByteArray(), Base64.NO_WRAP)
        val payload = Base64.encodeToString(
            "{\"sub\":\"$email\",\"iat\":${Date().time / 1000}}".toByteArray(StandardCharsets.UTF_8),
            Base64.NO_WRAP
        )
        return "$header.$payload.signature"
    }
}
