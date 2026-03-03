package com.tab.fitness.data.repo

import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.POST

@Serializable
data class AuthRequest(val email: String, val password: String)

@Serializable
data class AuthResponse(val token: String)

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: AuthRequest): AuthResponse

    @POST("auth/signup")
    suspend fun signup(@Body request: AuthRequest): AuthResponse
}
