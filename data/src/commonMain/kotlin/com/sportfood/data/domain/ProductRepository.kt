package com.sportfood.data.domain

import com.sportfood.shared.domain.product.Product
import com.sportfood.shared.util.RequestState
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getCurrentUserId(): String?

    fun readDiscountedProducts(): Flow<RequestState<List<Product>>>

    fun readNewProducts(): Flow<RequestState<List<Product>>>

    fun readProductById(id: String): Flow<RequestState<Product>>

    fun readProductsByIds(ids: List<String>): Flow<RequestState<List<Product>>>
}