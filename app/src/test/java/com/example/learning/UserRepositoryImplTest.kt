package com.example.learning

import com.example.learning.data.api.ApiService
import com.example.learning.data.model.Name
import com.example.learning.data.model.Picture
import com.example.learning.data.model.User
import com.example.learning.data.model.UserResponse
import com.example.learning.data.repository.UserRepository
import com.example.learning.data.repository.UserRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {

    private lateinit var userRepository: UserRepository
    private val mockApiService = mockk<ApiService>()

    @Before
    fun setUp() {
        userRepository = UserRepositoryImpl(mockApiService)
    }

    @Test
    fun `getUserData returns success result when data is available`() = runBlocking {
        // Arrange: Mock API service response with sample data
        val mockUserList = listOf(
            User(
                name = Name("", "John Doe", ""),
                email = "john.doe@example.com",
                picture = Picture("")
            ),
            User(
                name = Name("", "Jane Smith", ""),
                email = "jane.smith@example.com",
                picture = Picture("")
            )
        )
        val mockResponse = UserResponse(results = mockUserList)
        coEvery { mockApiService.fetchUsers(5) } returns mockResponse

        // Act: Call getUserData
        val result = userRepository.getUserData()

        // Assert: Check if result is a success and matches mock data
        assertTrue(result.isSuccess)
        assertEquals(mockUserList, result.getOrNull())
    }

    @Test
    fun `getUserData returns failure result when no data is found`() = runBlocking {
        // Arrange: Mock API service to return an empty list
        val mockResponse = UserResponse(results = emptyList())
        coEvery { mockApiService.fetchUsers(5) } returns mockResponse

        // Act: Call getUserData
        val result = userRepository.getUserData()

        // Assert: Check if result is a failure with the expected message
        assertTrue(result.isFailure)
        assertEquals("No user data found", result.exceptionOrNull()?.message)
    }

    @Test
    fun `getUserData returns failure result when API service throws an exception`() = runBlocking {
        // Arrange: Mock API service to throw an exception
        val errorMessage = "Network error"
        coEvery { mockApiService.fetchUsers(5) } throws Exception(errorMessage)

        // Act: Call getUserData
        val result = userRepository.getUserData()

        // Assert: Check if result is a failure and contains the correct exception message
        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
}
