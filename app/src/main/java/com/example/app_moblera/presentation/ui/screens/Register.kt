package com.example.app_moblera.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_moblera.R
import com.example.app_moblera.presentation.navigation.MenuDeAcciones
import com.example.app_moblera.presentation.ui.theme.VerdeLogo
import com.example.app_moblera.presentation.viewmodel.RegisterScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Register(
    navController: NavController,
    registerViewModel: RegisterScreenViewModel = koinViewModel()
) {
    val nombre by registerViewModel.nombre.collectAsStateWithLifecycle()
    val apellidos by registerViewModel.apellidos.collectAsStateWithLifecycle()
    val email by registerViewModel.email.collectAsStateWithLifecycle()
    val password by registerViewModel.password.collectAsStateWithLifecycle()
    val confirmPassword by registerViewModel.confirmPassword.collectAsStateWithLifecycle()
    val isChecked by registerViewModel.isTermsAccepted.collectAsStateWithLifecycle()

    val mensajeError by registerViewModel.mensajeError.collectAsStateWithLifecycle()
    val registroExitoso by registerViewModel.registroExitoso.collectAsStateWithLifecycle()

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmpasswordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(registroExitoso) {
        if (registroExitoso) {
            registerViewModel.clear() // Limpiamos el formulario por si vuelven a entrar
            navController.navigate("login") {
                popUpTo("registro") { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            MenuDeAcciones(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_moblera),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .size(170.dp)
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
                    text = "Registro",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextField(
                    value = nombre,
                    onValueChange = registerViewModel::setNombre,
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = apellidos,
                    onValueChange = registerViewModel::setApellidos,
                    label = { Text("Apellidos") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = email,
                    onValueChange = registerViewModel::setEmail,
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = password,
                    onValueChange = registerViewModel::setPassword,
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
                TextField(
                    value = confirmPassword,
                    onValueChange = registerViewModel::setConfirmPassword,
                    label = { Text("Repite la contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = if (confirmpasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = {
                            confirmpasswordVisible = !confirmpasswordVisible
                        }) {
                            Icon(
                                imageVector = if (confirmpasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = "Mostrar/Ocultar contraseña"
                            )
                        }
                    }
                )

                if (mensajeError.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = mensajeError,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = registerViewModel::setTermsAccepted,
                        colors = CheckboxDefaults.colors(
                            checkedColor = VerdeLogo
                        )
                    )
                    Text(text = "Acepto los terminos y condiciones")
                }
                Button(
                    onClick = {
                        registerViewModel.register()
                    },
                    enabled = nombre.isNotBlank() && apellidos.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank() && isChecked,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeLogo,
                        contentColor = Color.White,
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .height(50.dp)

                ) {
                    Text("Registrarse")
                }

            }
        }
    }
}