package com.bignerdranch.android.presentation.ui.login

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.presentation.R
import com.bignerdranch.android.presentation.ui.HomeViewModel
import com.bignerdranch.android.presentation.ui.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.regex.Pattern


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var vkButton: Button
    private lateinit var okButton: Button

    private val viewModel:HomeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)


        emailEditText = view.findViewById(R.id.emailInputField)
        passwordEditText = view.findViewById(R.id.passwordInputField)
        loginButton = view.findViewById(R.id.loginButton)
        vkButton = view.findViewById(R.id.button_vk)
        okButton = view.findViewById(R.id.button_ok)

        vkButton.setOnClickListener {
            openUrl("https://vk.com/")
        }
        okButton.setOnClickListener {
            openUrl("https://ok.ru/")
        }

        emailEditText.addTextChangedListener(createTextWatcher { validateEmail(it) })
        passwordEditText.addTextChangedListener(createTextWatcher { validatePassword(it) })

        loginButton.setOnClickListener {
            //change prmt
            viewModel.login("username@gmail.com", "password")

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.loginState.collect { state ->
                when (state) {
                    is LoginState.Idle -> { /* nohing to do */ }
                    is LoginState.Loading -> { /* show loading */ }
                    is LoginState.Success -> { navigateToMainScreen() }
                    is LoginState.Error -> { }
                }
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        val emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val isValid = emailPattern.matcher(email).matches()
        if (!isValid) {
            emailEditText.error = "Некорректный email"
        } else {
            emailEditText.error = null
        }
        return isValid
    }

    private fun validatePassword(password: String): Boolean {
        val isValid = password.isNotEmpty()
        if (!isValid) {
            passwordEditText.error = "Пароль не может быть пустым"
        } else {
            passwordEditText.error = null
        }
        return isValid
    }

    private fun validateFields() {
        val isEmailValid = validateEmail(emailEditText.text.toString())
        val isPasswordValid = validatePassword(passwordEditText.text.toString())
        loginButton.isEnabled = isEmailValid && isPasswordValid
    }

    private fun createTextWatcher(validator: (String) -> Boolean): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validator(s.toString())
                validateFields()
            }
        }
    }

    private fun navigateToMainScreen() {
//        findNavController().popBackStack()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.loginFragment, true)
            .build()
        findNavController().navigate(R.id.action_login_to_home_graph, null, navOptions)
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}