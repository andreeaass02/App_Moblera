package com.example.app_moblera.presentation.ui.screens

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home")
    data object LogIn : Screen("login")
    data object Register : Screen("registro")

    data object MainList : Screen("main_list")
    data object AddItem : Screen("add_item")

    data object ModifyItem : Screen("modify_item/{itemId}") {
        fun createRoute(itemId: Int) = "modify_item/$itemId"
    }
}