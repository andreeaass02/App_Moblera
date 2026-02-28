package com.example.app_moblera.presentation.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_moblera.presentation.ui.screens.*
import com.example.app_moblera.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {

        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.LogIn.route) {
            LogIn(navController = navController)
        }

        composable(Screen.Register.route) {
            Register(navController = navController)
        }

        composable(Screen.MainList.route) {
            MainListScreen(navController = navController, viewModel = mainViewModel)
        }

        composable(Screen.AddItem.route) {
            AddItemScreen(navController = navController, viewModel = mainViewModel)
        }


        composable(
            route = Screen.ModifyItem.route,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->

            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""

            ModifyItem(
                navController = navController,
                viewModel = mainViewModel,
                itemId = itemId
            )
        }
    }
}