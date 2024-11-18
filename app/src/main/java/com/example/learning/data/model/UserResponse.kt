package com.example.learning.data.model

data class UserResponse(
    val results: List<User>,
)

data class User(
    val name: Name,
    val email: String,
    val picture: Picture,
)

data class Name(
    val title: String,
    val first: String,
    val last: String,
)

data class Picture(
    val large: String,
)
