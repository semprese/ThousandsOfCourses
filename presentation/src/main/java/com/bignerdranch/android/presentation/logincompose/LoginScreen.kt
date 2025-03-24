package com.bignerdranch.android.presentation.logincompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.presentation.R
import com.bignerdranch.android.presentation.logincompose.theme.ThousandsOfCoursesTheme

@Composable
fun LoginScreen(
    loginState: LoginState,
    onLogin: (String, String) -> Unit,
    onLoginSuccess: () -> Unit,
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isEmailValid = remember(email) {
        email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"))
    }

    val isButtonEnabled = isEmailValid && password.isNotEmpty()
    val textState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Вход",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp)

        )
        Text(
            text = "Email",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        Box(contentAlignment = Alignment.CenterStart) {
            BasicTextField(
                value = email,
                onValueChange = { textState.value = it },
                modifier = Modifier
                    .width(380.dp)
                    .height(40.dp)
                    .background(Color.DarkGray, RoundedCornerShape(30.dp))
                    .padding(8.dp),
                textStyle = LocalTextStyle.current.copy(
                    color = Color.White,
                    fontSize = 14.sp
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
            )
            if (email.isBlank())
                Text(
                    "example@gmail.com",
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
        }

        if (email.isNotEmpty() && !isEmailValid) {
            Text(
                text = "Введите корректный email",
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Пароль",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        Box(contentAlignment = Alignment.CenterStart) {
            BasicTextField(
                value = password,
                onValueChange = { textState.value = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .width(380.dp)
                    .height(40.dp)
                    .background(Color.DarkGray, RoundedCornerShape(30.dp))
                    .padding(8.dp),
                textStyle = LocalTextStyle.current.copy(
                    color = Color.White,
                    fontSize = 14.sp
                ),
                singleLine = true
            )
            if (password.isBlank())
                Text(
                    "Введите пароль",
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {
                onLogin(email, password)
            },
            colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.Green),
            enabled = isButtonEnabled,
            modifier = Modifier
                .width(380.dp)
                .padding(vertical = 8.dp)
                .height(40.dp)
        ) {
            Text("Вход")
        }
        Row(
            Modifier.padding(8.dp)
        ) {
            Text(
                text = "Нету аккаунта? ",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Регистрация",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Green),
                modifier = Modifier.clickable {  }
            )
        }
        Text(
            text = "Забыл пароль",
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Green),
            modifier = Modifier.clickable {  }
        )
        Spacer(modifier = Modifier.height(30.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(40.dp))
        
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .height(40.dp)
        ){
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.buttonVK),
                    disabledContainerColor = colorResource(R.color.buttonVK),
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .background(
                        color = colorResource(R.color.buttonVK),
                        shape = RoundedCornerShape(30.dp)
                    )
            ) {
            }
            Spacer(Modifier.width(20.dp))
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf<Color>(
                                colorResource(R.color.buttonOKtop),
                                colorResource(R.color.buttonOKbottom)
                            )
                        ),
                        shape = RoundedCornerShape(30.dp)
                    )
//                    .padding(vertical = 8.dp)
            ) {
                Text("Вход")
            }
        }


        when (val state = loginState) {
            is LoginState.Loading -> {
                CircularProgressIndicator()
            }

            is LoginState.Success -> {
                LaunchedEffect(state) {
                    onLoginSuccess() // Переход на главный экран
                }
            }

            is LoginState.Error -> {
                Text(text = state.message, color = Color.Red)
            }

            else -> {}
        }
    }
}

@Preview
@Composable
private fun PrevHomeScreen() {
    ThousandsOfCoursesTheme {
        LoginScreen(
            loginState = LoginState.Idle,
            onLogin = { s: String, s1: String -> },
            onLoginSuccess = {}
        )
    }
}
