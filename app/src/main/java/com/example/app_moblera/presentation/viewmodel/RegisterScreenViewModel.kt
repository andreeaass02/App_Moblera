package com.example.app_moblera.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_moblera.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterScreenViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre.asStateFlow()

    private val _apellidos = MutableStateFlow("")
    val apellidos: StateFlow<String> = _apellidos.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    private val _isTermsAccepted = MutableStateFlow(false)
    val isTermsAccepted: StateFlow<Boolean> = _isTermsAccepted.asStateFlow()

    private val _mensajeError = MutableStateFlow("")
    val mensajeError: StateFlow<String> = _mensajeError.asStateFlow()

    private val _registroExitoso = MutableStateFlow(false)
    val registroExitoso: StateFlow<Boolean> = _registroExitoso.asStateFlow()

    fun setNombre(nombre: String) { _nombre.value = nombre }
    fun setApellidos(apellidos: String) { _apellidos.value = apellidos }
    fun setEmail(email: String) { _email.value = email }
    fun setPassword(password: String) { _password.value = password }
    fun setConfirmPassword(confirmPassword: String) { _confirmPassword.value = confirmPassword }
    fun setTermsAccepted(accepted: Boolean) { _isTermsAccepted.value = accepted }

    fun register() {
        if (!_isTermsAccepted.value) {
            _mensajeError.value = "Debes aceptar los términos y condiciones."
            return
        }
        if (_password.value != _confirmPassword.value) {
            _mensajeError.value = "Las contraseñas no coinciden."
            return
        }
        if (_password.value.length < 6) {
            _mensajeError.value = "La contraseña debe tener al menos 6 caracteres."
            return
        }
        if (_email.value.isEmpty() || _nombre.value.isEmpty() || _apellidos.value.isEmpty()) {
            _mensajeError.value = "Por favor, rellena todos los campos."
            return
        }

        viewModelScope.launch {
            val exito = authRepository.register(_email.value, _password.value)
            if (exito) {
                _mensajeError.value = ""
                _registroExitoso.value = true
            } else {
                _mensajeError.value = "Error: El correo ya está registrado o no es válido."
            }
        }
    }

    fun clear() {
        _nombre.value = ""
        _apellidos.value = ""
        _email.value = ""
        _password.value = ""
        _confirmPassword.value = ""
        _isTermsAccepted.value = false
        _mensajeError.value = ""
        _registroExitoso.value = false
    }
}