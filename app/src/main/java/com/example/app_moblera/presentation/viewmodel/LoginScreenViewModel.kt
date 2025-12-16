package com.example.app_moblera.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginScreenViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email   

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password


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

}