package com.example.learning.presentation.state

import com.example.learning.data.model.User


// UserViewState.kt
sealed class UserViewState {
    data object Idle : UserViewState()
    data object Loading : UserViewState()
    data class Success(val user: List<User>) : UserViewState()
    data class Error(val error: Throwable) : UserViewState()
}
