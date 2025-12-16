package com.example.app_moblera.presentation.ui.screens

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home")
    data object LogIn : Screen("login")
}
