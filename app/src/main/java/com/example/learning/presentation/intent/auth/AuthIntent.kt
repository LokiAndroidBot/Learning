package com.example.learning.presentation.intent.auth

sealed class AuthIntent {
    data class Login(val username: String, val password: String) : AuthIntent()
    data class Signup(val username: String, val password: String, val confirmPassword: String) :
        AuthIntent()
}
