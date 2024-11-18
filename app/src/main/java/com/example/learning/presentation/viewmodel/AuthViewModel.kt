package com.example.learning.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learning.domain.auth.AuthUseCase
import com.example.learning.presentation.intent.UiEvent
import com.example.learning.presentation.state.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    // Update username
    fun updateUsername(username: String) {
        _state.value = _state.value.copy(username = username)
    }

    // Update password
    fun updatePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    // Handle login intent
    fun onLogin(username: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val result = authUseCase.executeLogin(username, password)
            if (result.isSuccess) {
                _uiEvent.emit(UiEvent.NavigateTo("user_app_screen"))
            } else {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
                )
            }
        }
    }
}
