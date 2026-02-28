package com.example.app_moblera.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.app_moblera.presentation.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDeAcciones(navController: NavController) {

    var expanded by remember { mutableStateOf(false) }

    val ColorMoblera = Color(0xFF795548)

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ColorMoblera,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        title = { Text("App Moblera") },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Abrir menú"
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Inicio") },
                    onClick = {
                        expanded = false
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.HomeScreen.route) { inclusive = true }
                        }
                    }
                )

                DropdownMenuItem(
                    text = { Text("Iniciar Sesión") },
                    onClick = {
                        expanded = false
                        navController.navigate(Screen.LogIn.route)
                    }
                )

                DropdownMenuItem(
                    text = { Text("Registrarse") },
                    onClick = {
                        expanded = false
                        navController.navigate(Screen.Register.route)
                    }
                )

                HorizontalDivider()

                DropdownMenuItem(
                    text = { Text("Volver") },
                    onClick = {
                        expanded = false
                        navController.popBackStack()
                    }
                )

                DropdownMenuItem(
                    text = { Text("Cerrar Menú") },
                    onClick = { expanded = false }
                )
            }
        }
    )
}