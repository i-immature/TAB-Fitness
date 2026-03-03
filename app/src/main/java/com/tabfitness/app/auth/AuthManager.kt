package com.tabfitness.app.auth

import android.content.Context
import android.util.Base64
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets

class AuthManager(context: Context) {
    private val prefs = context.getSharedPreferences("tab-auth", Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean = prefs.getString(KEY_TOKEN, null) != null

    fun signupOrLogin(email: String, password: String): Boolean {
        if (email.isBlank() || password.length < 6) return false
        val header = Base64.encodeToString("{".toByteArray(), Base64.NO_WRAP) // symbolic header
        val payload = Base64.encodeToString(
            Json.encodeToString(JwtPayload(sub = email, role = "user", app = "TAB")).toByteArray(StandardCharsets.UTF_8),
            Base64.NO_WRAP
        )
        val token = "$header.$payload.local-signature"
        prefs.edit().putString(KEY_TOKEN, token).apply()
        return true
    }

    fun logout() {
        prefs.edit().remove(KEY_TOKEN).apply()
    }

    fun token(): String? = prefs.getString(KEY_TOKEN, null)

    @Serializable
    private data class JwtPayload(val sub: String, val role: String, val app: String)

    private companion object {
        const val KEY_TOKEN = "jwt"
    }
}
