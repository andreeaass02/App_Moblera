package com.example.app_moblera.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterScreenViewModel : ViewModel() {
    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre

    private val _apellidos = MutableStateFlow("")
    val apellidos: StateFlow<String> = _apellidos

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    private val _isTermsAccepted = MutableStateFlow(false)
    val isTermsAccepted: StateFlow<Boolean> = _isTermsAccepted

    fun setNombre(nombre: String) {
        _nombre.value = nombre
    }

    fun setApellidos(apellidos: String) {
        _apellidos.value = apellidos
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setConfirmPassword(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }

    fun setTermsAccepted(accepted: Boolean) {
        _isTermsAccepted.value = accepted
    }

    fun clear() {
        _nombre.value = ""
        _apellidos.value = ""
        _email.value = ""
        _password.value = ""
        _confirmPassword.value = ""
        _isTermsAccepted.value = false
    }
}
