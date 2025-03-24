package com.bignerdranch.android.data.api

import com.bignerdranch.android.domain.model.LoginRequest
import com.bignerdranch.android.domain.model.LoginResponse

import retrofit2.Response
import retrofit2.http.GET

interface AuthApi {
    @GET("uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun login(): Response<LoginResponse>
}