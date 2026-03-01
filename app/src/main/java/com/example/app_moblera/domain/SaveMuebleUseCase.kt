package com.example.app_moblera.domain

import com.example.app_moblera.domain.repositories.MuebleFirestoreRepository
import com.example.app_moblera.model.MuebleItem

class SaveMuebleUseCase(private val repository: MuebleFirestoreRepository) {
    suspend operator fun invoke(mueble: MuebleItem): Boolean {
        return repository.save(mueble)
    }
}