package com.example.app_moblera.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_moblera.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password
    private val _mensajeError = MutableStateFlow("")
    val mensajeError: StateFlow<String> = _mensajeError.asStateFlow()

    private val _loginExitoso = MutableStateFlow(false)
    val loginExitoso: StateFlow<Boolean> = _loginExitoso.asStateFlow()


    fun setEmail(username: String) {
        _email.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun clear() {
        _email.value = ""
        _password.value = ""
    }
    fun login() {
        viewModelScope.launch {
            val exito = authRepository.login(email.value, password.value)
            if (exito) {
                _mensajeError.value = ""
                _loginExitoso.value = true
            } else {
                _mensajeError.value = "Error: El usuario no existe o los datos son incorrectos."
            }
        }
    }

}