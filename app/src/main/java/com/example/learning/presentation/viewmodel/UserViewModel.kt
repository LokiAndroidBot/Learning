package com.example.learning.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learning.domain.FetchUserDataUseCase
import com.example.learning.presentation.intent.UserIntent
import com.example.learning.presentation.state.UserViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// UserViewModel.kt
@HiltViewModel
class UserViewModel @Inject constructor(
    private val fetchUserDataUseCase: FetchUserDataUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<UserViewState>(UserViewState.Idle)
    val viewState: StateFlow<UserViewState> = _viewState

    fun handleIntent(intent: UserIntent) {
        when (intent) {
            is UserIntent.FetchUsers -> fetchUser("10")
        }
    }

    private fun fetchUser(userId: String) {
        viewModelScope.launch {
            _viewState.value = UserViewState.Loading
            val result = fetchUserDataUseCase()
            _viewState.value = if (result.isSuccess) {
                UserViewState.Success(result.getOrNull()!!)
            } else {
                UserViewState.Error(result.exceptionOrNull() ?: Throwable("Unknown Error"))
            }
        }
    }
}

