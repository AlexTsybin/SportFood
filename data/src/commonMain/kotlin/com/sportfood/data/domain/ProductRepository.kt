package com.sportfood.data.domain

import com.sportfood.shared.domain.product.Product
import com.sportfood.shared.domain.product.ProductCategory
import com.sportfood.shared.util.RequestState
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getCurrentUserId(): String?

    fun readDiscountedProductsFlow(): Flow<RequestState<List<Product>>>

    fun readNewProductsFlow(): Flow<RequestState<List<Product>>>

    fun readProductByIdFlow(id: String): Flow<RequestState<Product>>

    fun readProductsByIdsFlow(ids: List<String>): Flow<RequestState<List<Product>>>

    fun readProductsByCategoryFlow(category: ProductCategory): Flow<RequestState<List<Product>>>
}