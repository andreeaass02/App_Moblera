package com.example.app_moblera.domain

import com.example.app_moblera.domain.repositories.MuebleFirestoreRepository

class DeleteMuebleUseCase(private val repository: MuebleFirestoreRepository) {
    suspend operator fun invoke(id: String): Boolean {
        return repository.delete(id)
    }

}