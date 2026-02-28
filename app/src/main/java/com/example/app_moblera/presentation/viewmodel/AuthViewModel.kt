package com.example.app_moblera.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_moblera.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _mensajeError = MutableStateFlow("")
    val mensajeError: StateFlow<String> = _mensajeError.asStateFlow()

    private val _loginExitoso = MutableStateFlow(false)
    val loginExitoso: StateFlow<Boolean> = _loginExitoso.asStateFlow()

    fun login(email: String, contrasena: String) {
        if (email.isEmpty() || contrasena.isEmpty()) {
            _mensajeError.value = "Por favor, rellena todos los campos."
            return
        }

        viewModelScope.launch {
            val exito = authRepository.login(email, contrasena)
            if (exito) {
                _mensajeError.value = "" // Borramos el error
                _loginExitoso.value = true // ¡Pa' dentro!
            } else {
                // EL MENSAJE QUE PEDISTE:
                _mensajeError.value = "El usuario no existe o la contraseña es incorrecta."
            }
        }
    }

    fun register(email: String, contrasena: String) {
        if (email.isEmpty() || contrasena.isEmpty()) {
            _mensajeError.value = "Por favor, rellena todos los campos."
            return
        }

        viewModelScope.launch {
            val exito = authRepository.register(email, contrasena)
            if (exito) {
                _mensajeError.value = ""
                _loginExitoso.value = true // Al registrarse, entra directo
            } else {
                _mensajeError.value = "Error al registrar. Quizás el correo ya exista o la clave sea muy corta."
            }
        }
    }
}