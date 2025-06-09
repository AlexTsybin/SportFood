package com.sportfood.data.domain

import com.sportfood.shared.domain.product.Product
import dev.gitlive.firebase.storage.File

interface AdminRepository {

    fun getCurrentUserId(): String?

    suspend fun createNewProduct(
        product: Product,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    )

    suspend fun uploadImageToStorage(file: File): String?
}