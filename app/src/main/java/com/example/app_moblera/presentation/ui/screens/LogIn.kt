package com.example.app_moblera.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_moblera.R
import com.example.app_moblera.presentation.navigation.MenuDeAcciones
import com.example.app_moblera.presentation.ui.theme.VerdeLogo
import com.example.app_moblera.presentation.viewmodel.LoginScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LogIn(
    navController: NavController,
    loginScreenViewModel: LoginScreenViewModel = koinViewModel()
) {
    val email by loginScreenViewModel.email.collectAsState()
    val password by loginScreenViewModel.password.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }

    val mensajeError by loginScreenViewModel.mensajeError.collectAsState()
    val loginExitoso by loginScreenViewModel.loginExitoso.collectAsState()

    LaunchedEffect(loginExitoso) {
        if (loginExitoso) {
            navController.navigate(Screen.MainList.route) {
                popUpTo(Screen.LogIn.route) { inclusive = true }
            }
        }
    }

    Scaffold (
        topBar = {
            MenuDeAcciones(navController)
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1.0f))

            Image(
                painter = painterResource(id = R.drawable.logo_moblera),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .background(Color(0xFFD3B8AE))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Iniciar sesión",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                TextField(
                    value = email,
                    onValueChange = { loginScreenViewModel.setEmail(it) },
                    label = { Text("Correo electrónico / Usuario") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = password,
                    onValueChange = { loginScreenViewModel.setPassword(it) },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = "Mostrar/Ocultar contraseña"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (mensajeError.isNotEmpty()) {
                    Text(
                        text = mensajeError,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { navController.navigate("registro") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = VerdeLogo,
                            contentColor = Color.White,
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text("Registro")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            loginScreenViewModel.login()
                        },
                        enabled = email.isNotBlank() && password.length >= 6,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = VerdeLogo,
                            contentColor = Color.White,
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text("Login")
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1.5f))
        }
    }
}