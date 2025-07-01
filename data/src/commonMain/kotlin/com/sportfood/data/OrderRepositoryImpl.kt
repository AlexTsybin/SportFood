package com.sportfood.data

import com.sportfood.data.domain.CustomerRepository
import com.sportfood.data.domain.OrderRepository
import com.sportfood.shared.domain.Order
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore

class OrderRepositoryImpl(
    private val customerRepository: CustomerRepository,
) : OrderRepository {

    override fun getCurrentUserId() = Firebase.auth.currentUser?.uid

    override suspend fun createOrder(
        order: Order,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val userId = getCurrentUserId()
            if (userId != null) {
                val database = Firebase.firestore
                val orderCollection = database.collection(collectionPath = "order")
                orderCollection.document(order.id).set(order)
                customerRepository.deleteAllCartItems(
                    onSuccess = {},
                    onError = {}
                )
                onSuccess()
            } else {
                onError("User is not available")
            }
        } catch (e: Exception) {
            onError("Error while creating order: ${e.message}")
        }
    }
}