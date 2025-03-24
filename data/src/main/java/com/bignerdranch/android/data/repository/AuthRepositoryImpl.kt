package com.bignerdranch.android.data.repository

import com.bignerdranch.android.data.api.AuthApi
import com.bignerdranch.android.domain.model.LoginRequest
import com.bignerdranch.android.domain.model.LoginResponse
import com.bignerdranch.android.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
) : IAuthRepository {

    override suspend fun login(request: LoginRequest): Flow<LoginResponse> = flow {
        val response = authApi.login()
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            throw Exception("Login failed: ${response.errorBody()?.string()}")
        }
    }
}