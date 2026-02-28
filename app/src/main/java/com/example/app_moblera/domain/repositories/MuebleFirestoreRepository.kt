package com.example.app_moblera.domain.repositories

import com.example.app_moblera.model.MuebleItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class MuebleFirestoreRepository(private val firestore: FirebaseFirestore) {

    private val mueblesCollection = firestore.collection("muebles")

    fun list(): Flow<List<MuebleItem>> {
        return queryForList(mueblesCollection, MuebleItem::class.java)
    }

    suspend fun save(mueble: MuebleItem): Boolean {
        return try {
            if (mueble.id.isEmpty()) {
                mueblesCollection.add(mueble).await()
            } else {
                mueblesCollection.document(mueble.id).set(mueble).await()
            }
            true
        } catch (e: Exception) {
            false
        }
    }
    suspend fun update(id: String, nombre: String, descripcion: String): Boolean {
        return try {
            val actualizaciones = mapOf(
                "nombre" to nombre,
                "descripcion" to descripcion
            )
            mueblesCollection.document(id).update(actualizaciones).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun delete(id: String): Boolean {
        return try {
            mueblesCollection.document(id).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun <T> queryForList(query: Query, clazz: Class<T>): Flow<List<T>> {
        return callbackFlow {
            val listener = query.addSnapshotListener { snapshots, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val items = snapshots?.documents?.mapNotNull { doc ->
                    doc.toObject(clazz)
                } ?: emptyList()

                trySend(items)
            }
            awaitClose { listener.remove() }
        }
    }
}