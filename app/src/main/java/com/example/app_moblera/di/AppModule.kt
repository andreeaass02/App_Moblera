package com.example.app_moblera.di

import com.example.app_moblera.domain.*
import com.example.app_moblera.domain.repositories.AuthRepository
import com.example.app_moblera.domain.repositories.MuebleFirestoreRepository
import com.example.app_moblera.presentation.viewmodel.AuthViewModel
import com.example.app_moblera.presentation.viewmodel.LoginScreenViewModel
import com.example.app_moblera.presentation.viewmodel.MainViewModel
import com.example.app_moblera.presentation.viewmodel.RegisterScreenViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Firebase.firestore }

    single { MuebleFirestoreRepository(get()) }
    single { FirebaseAuth.getInstance() }

    single { AuthRepository(get()) }

    viewModel { AuthViewModel(get()) }

    factory { GetMueblesUseCase(get()) }
    factory { SaveMuebleUseCase(get()) }
    factory { DeleteMuebleUseCase(get()) }

    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { LoginScreenViewModel(get()) }
    viewModel { RegisterScreenViewModel(get()) }
}