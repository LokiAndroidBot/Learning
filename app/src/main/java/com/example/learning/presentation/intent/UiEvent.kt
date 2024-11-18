package com.example.learning.presentation.intent

sealed class UiEvent {
    data class NavigateTo(val route: String) : UiEvent()
    data class ShowError(val message: String) : UiEvent()
}
