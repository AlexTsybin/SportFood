package com.sportfood.data.domain

import com.sportfood.shared.domain.Order

interface OrderRepository {

    fun getCurrentUserId(): String?

    suspend fun createOrder(
        order: Order,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    )
}