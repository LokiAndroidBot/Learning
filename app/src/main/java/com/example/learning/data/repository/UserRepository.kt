package com.example.learning.data.repository

import com.example.learning.data.api.ApiService
import com.example.learning.data.model.User
import javax.inject.Inject

// UserRepository.kt
interface UserRepository {
    suspend fun getUserData(): Result<List<User>>
}

// UserRepositoryImpl.kt
class UserRepositoryImpl @Inject constructor(private val apiService: ApiService) : UserRepository {

    override suspend fun getUserData(): Result<List<User>> {
        return try {
            val response = apiService.fetchUsers(5)
            if (response.results.isNotEmpty()) {
                Result.success(response.results)
            } else {
                Result.failure(Exception("No user data found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
