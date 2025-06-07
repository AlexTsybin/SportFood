package com.sportfood.data.domain

import com.sportfood.shared.domain.product.Product

interface AdminRepository {

    fun getCurrentUserId(): String?

    suspend fun createNewProduct(
        product: Product,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    )
}