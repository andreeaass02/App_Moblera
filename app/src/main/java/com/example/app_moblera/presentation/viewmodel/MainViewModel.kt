package com.example.app_moblera.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_moblera.domain.DeleteMuebleUseCase
import com.example.app_moblera.domain.GetMueblesUseCase
import com.example.app_moblera.domain.SaveMuebleUseCase
import com.example.app_moblera.model.MuebleItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getMueblesUseCase: GetMueblesUseCase,
    private val saveMuebleUseCase: SaveMuebleUseCase,
    private val deleteMuebleUseCase: DeleteMuebleUseCase
) : ViewModel() {

    private val _items = MutableStateFlow<List<MuebleItem>>(emptyList())
    val items: StateFlow<List<MuebleItem>> = _items.asStateFlow()

    init {
        observeMuebles()
    }

    private fun observeMuebles() {
        viewModelScope.launch {
            getMueblesUseCase().collect { listaDesdeFirebase ->
                _items.value = listaDesdeFirebase
            }
        }
    }

    fun addItem(nombre: String, descripcion: String) {
        viewModelScope.launch {
            val newItem = MuebleItem(nombre = nombre, descripcion = descripcion)
            saveMuebleUseCase(newItem)
        }
    }

    fun deleteItem(id: String) {
        viewModelScope.launch {
            deleteMuebleUseCase(id)
        }
    }

    fun toggleExpand(id: String) {
        _items.update { list ->
            list.map { item ->
                if (item.id == id) item.copy(isExpanded = !item.isExpanded) else item
            }
        }
    }

    fun getMuebleById(id: String): MuebleItem? {
        return _items.value.find { it.id == id }
    }

    fun updateMueble(id: String, nuevoNombre: String, nuevaDescripcion: String) {
        viewModelScope.launch {
            val updatedItem = MuebleItem(id = id, nombre = nuevoNombre, descripcion = nuevaDescripcion)
            saveMuebleUseCase(updatedItem)
        }
    }
}