package com.example.learning.data.api

import com.example.learning.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun fetchUsers(@Query("results") results: Int = 5): UserResponse
}
