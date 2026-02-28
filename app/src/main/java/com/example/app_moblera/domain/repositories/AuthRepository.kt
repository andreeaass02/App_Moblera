package com.example.app_moblera.domain.repositories

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository(private val auth: FirebaseAuth) {

    suspend fun register(email: String, contrasena: String): Boolean {
        return try {
            auth.createUserWithEmailAndPassword(email, contrasena).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun login(email: String, contrasena: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, contrasena).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}