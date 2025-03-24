package com.bignerdranch.android.domain.model

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val courses: List<Course>
)

data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
)