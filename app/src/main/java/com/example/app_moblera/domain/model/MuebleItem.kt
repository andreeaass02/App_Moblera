package com.example.app_moblera.model

import com.google.firebase.firestore.DocumentId

data class MuebleItem(
    @DocumentId
    val id: String = "",
    val nombre: String = "",
    val descripcion: String = "",
    val isExpanded: Boolean = false
){
    constructor() : this("", "", "", false)
}