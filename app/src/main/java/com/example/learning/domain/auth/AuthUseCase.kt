package com.example.learning.domain.auth

import com.example.learning.data.repository.auth.AuthRepository
import com.google.assistant.appactions.testing.aatl.converter.ValidationException
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun executeLogin(username: String, password: String): Result<Unit> {
        if (!isValidUsername(username)) {
            return Result.failure(ValidationException("Invalid username format"))
        }
        if (password.length < 6) {
            return Result.failure(ValidationException("Password must be at least 6 characters"))
        }
        return authRepository.login(username, password)
    }

    suspend fun executeSignup(
        username: String,
        password: String,
        confirmPassword: String
    ): Result<Unit> {
        if (password != confirmPassword) {
            return Result.failure(ValidationException("Passwords do not match"))
        }
        if (!isValidUsername(username)) {
            return Result.failure(ValidationException("Invalid username format"))
        }
        return authRepository.signup(username, password, confirmPassword)
    }

    private fun isValidUsername(username: String): Boolean {
        // Example validation: username must contain '@' symbol
        return username.length > 3
    }
}
