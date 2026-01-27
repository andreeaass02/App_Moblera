package com.example.app_moblera.model

data class MuebleItem(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val isExpanded: Boolean = false
)