package com.example.app_moblera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.app_moblera.presentation.navigation.NavGraph
import com.example.app_moblera.presentation.ui.theme.App_MobleraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_MobleraTheme {
                NavGraph()
            }
        }
    }
}