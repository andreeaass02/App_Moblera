package com.example.app_moblera.domain

import com.example.app_moblera.domain.repositories.MuebleFirestoreRepository
import com.example.app_moblera.model.MuebleItem
import kotlinx.coroutines.flow.Flow

class GetMueblesUseCase(private val repository: MuebleFirestoreRepository) {
    operator fun invoke(): Flow<List<MuebleItem>> {
        return repository.list()
    }
}