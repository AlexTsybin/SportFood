package com.sportfood.data.domain

import com.sportfood.shared.domain.CartItem
import com.sportfood.shared.domain.Customer
import com.sportfood.shared.util.RequestState
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    fun getCurrentUserId(): String?

    suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    )

    suspend fun updateCustomer(
        customer: Customer,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )

    fun readCustomerFlow(): Flow<RequestState<Customer>>

    suspend fun addItemToCart(
        cartItem: CartItem,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    )

    suspend fun updateCartItemQuantity(
        id: String,
        quantity: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    )

    suspend fun deleteCartItem(
        id: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    )

    suspend fun signOut(): RequestState<Unit>
}