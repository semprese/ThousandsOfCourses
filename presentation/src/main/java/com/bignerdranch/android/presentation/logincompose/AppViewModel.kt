package com.bignerdranch.android.presentation.logincompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.bignerdranch.android.data.api.AuthApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ComposeViewModel (
    private val authApi: AuthApi
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
//                val response = authApi.login(
//                    com.bignerdranch.android.domain.model.LoginRequest(
//                        username,
//                        password
//                    )
//                )
//                if (response.isSuccessful) {
//                    _loginState.value = LoginState.Success(response.body()!!)
//                } else {
//                    _loginState.value = LoginState.Error("Login failed")
//                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val response: com.bignerdranch.android.domain.model.LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}