package com.example.learning.domain

import com.example.learning.data.model.User
import com.example.learning.data.repository.UserRepository

class FetchUserUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Result<List<User>> {
        return try {
            userRepository.getUserData()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
