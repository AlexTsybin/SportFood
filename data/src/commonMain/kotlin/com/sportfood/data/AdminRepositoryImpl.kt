package com.sportfood.data

import com.sportfood.data.domain.AdminRepository
import com.sportfood.shared.domain.product.Product
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore

class AdminRepositoryImpl : AdminRepository {

    override fun getCurrentUserId(): String? = Firebase.auth.currentUser?.uid

    override suspend fun createNewProduct(
        product: Product,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val currentUserId = getCurrentUserId()
            if (currentUserId != null) {
                val firestore = Firebase.firestore
                val productCollection = firestore.collection(collectionPath = "product")
                productCollection.document(product.id).set(product)
                onSuccess()
            } else {
                onError("User is not available")
            }
        } catch (e: Exception) {
            onError("Error while creating a new product: ${e.message}")
        }
    }
}