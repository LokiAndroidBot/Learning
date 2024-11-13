package com.example.learning.presentation.intent


sealed class UserIntent {
    data object FetchUsers : UserIntent()
}