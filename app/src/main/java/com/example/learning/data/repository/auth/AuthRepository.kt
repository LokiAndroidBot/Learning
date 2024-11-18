package com.example.learning.data.repository.auth

import com.example.learning.data.api.ApiService
import javax.inject.Inject


interface AuthRepository {
    suspend fun login(username: String, password: String): Result<Unit>
    suspend fun signup(username: String, password: String, confirmPassword: String): Result<Unit>
}

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<Unit> {
        // Perform login API call or logic here
        return try {
            // Simulating a network call (you would replace this with an actual API call)
            val response = mockNetworkCall(username, password)

            if (response.isSuccessful) {
                Result.success(Unit) // Login successful, no data to return
            } else {
                Result.failure(Exception("Login failed")) // Login failed, returning an error
            }
        } catch (e: Exception) {
            Result.failure(e) // Catching any exception that occurred during the network call
        }
        //return apiService.login(username, password)
    }

    override suspend fun signup(
        username: String, password: String, confirmPassword: String
    ): Result<Unit> {
        // Perform signup API call or logic here
        // Step 1: Check if passwords match
        if (password != confirmPassword) {
            return Result.failure(Exception("Passwords do not match"))
        }

        return try {
            // Simulating network call to perform signup
            val response = mockNetworkSignupCall(username, password)

            if (response.isSuccessful) {
                Result.success(Unit) // Signup successful, no data to return
            } else {
                Result.failure(Exception("Signup failed"))
            }
        } catch (e: Exception) {
            Result.failure(e) // Handle network failure or other exceptions
        }
        //return apiService.signup(username, password, confirmPassword)
    }
}

private fun mockNetworkSignupCall(username: String, password: String): Response {
    // Simulate success or failure based on username and password
    return if (username.isNotEmpty() && password.length >= 6) {
        Response(true)
    } else {
        Response(false)
    }
}

// Mock function for demonstration
private fun mockNetworkCall(username: String, password: String): Response {
    // Simulate success or failure based on username and password
    return if (username == "user" && password == "password") {
        Response(true)
    } else {
        Response(false)
    }
}

data class Response(val isSuccessful: Boolean)