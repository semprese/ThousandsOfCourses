package com.bignerdranch.android.domain.repository

import com.bignerdranch.android.domain.model.LoginRequest
import com.bignerdranch.android.domain.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun login(request: LoginRequest): Flow<LoginResponse>
}