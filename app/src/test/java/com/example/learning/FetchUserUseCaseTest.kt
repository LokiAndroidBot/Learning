package com.example.learning

import com.example.learning.data.model.Name
import com.example.learning.data.model.Picture
import com.example.learning.data.model.User
import com.example.learning.data.repository.UserRepository
import com.example.learning.domain.FetchUserUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchUserUseCaseTest {

    // Mock the UserRepository
    private val userRepository = mockk<UserRepository>()

    // Create the use case with the mocked repository
    private val fetchUserUseCase = FetchUserUseCase(userRepository)

    @Test
    fun `invoke should return success result when userRepository returns data`() = runBlocking {
        // Arrange: Set up the mocked data
        val mockUserList = listOf(
            User(
                Name("", "John Doe", ""),
                email = "johndoe@example.com",
                Picture(""),
            ),
        )
        coEvery { userRepository.getUserData() } returns Result.success(mockUserList)

        // Act: Call the use case
        val result = fetchUserUseCase()

        // Assert: Verify the result is successful and contains the expected data
        assertEquals(Result.success(mockUserList), result)
    }

    @Test
    fun `invoke should return failure result when userRepository throws an exception`() =
        runBlocking {
            // Arrange: Set up an exception for the mock
            val exception = Exception("Network error")
            coEvery { userRepository.getUserData() } throws exception

            // Act: Call the use case
            val result = fetchUserUseCase()

            // Assert: Verify the result is a failure and contains the expected exception
            assertEquals(Result.failure<List<User>>(exception), result)
        }
}
