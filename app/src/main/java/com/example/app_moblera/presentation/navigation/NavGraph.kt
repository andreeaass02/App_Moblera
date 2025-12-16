package com.example.app_moblera.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_moblera.presentation.ui.screens.HomeScreen
import com.example.app_moblera.presentation.ui.screens.LogIn
import com.example.app_moblera.presentation.ui.screens.Register
import com.example.app_moblera.presentation.ui.screens.Screen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("login") {
            LogIn(navController = navController)
        }

        composable ("registro"){
            Register(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavGraphPreview() {
    NavGraph()
}