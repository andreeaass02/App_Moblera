package com.example.app_moblera.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.app_moblera.model.MuebleItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<MuebleItem>>(
        listOf(
            MuebleItem(1, "Sofá Chester", "Sofá de cuero clásico de 3 plazas."),
            MuebleItem(2, "Mesa de Roble", "Mesa de comedor para 6 personas.")
        )
    )
    val items: StateFlow<List<MuebleItem>> = _items.asStateFlow()

    fun addItem(nombre: String, descripcion: String) {
        val newId = (_items.value.maxOfOrNull { it.id } ?: 0) + 1
        val newItem = MuebleItem(newId, nombre, descripcion)
        _items.update { it + newItem }
    }

    fun deleteItem(id: Int) {
        _items.update { list -> list.filter { it.id != id } }
    }

    fun toggleExpand(id: Int) {
        _items.update { list ->
            list.map { item ->
                if (item.id == id) item.copy(isExpanded = !item.isExpanded) else item
            }
        }
    }

    fun getMuebleById(id: Int): MuebleItem? {
        return _items.value.find { it.id == id }
    }

    fun updateMueble(id: Int, nuevoNombre: String, nuevaDescripcion: String) {
        _items.update { list ->
            list.map { item ->
                if (item.id == id) {
                    item.copy(nombre = nuevoNombre, descripcion = nuevaDescripcion)
                } else {
                    item
                }
            }
        }
    }
}