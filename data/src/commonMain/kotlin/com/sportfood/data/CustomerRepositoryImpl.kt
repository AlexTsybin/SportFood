package com.sportfood.data

import com.alextsy.shared.domain.Customer
import com.sportfood.data.domain.CustomerRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore

class CustomerRepositoryImpl : CustomerRepository {

    override fun getCurrentUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }

    override suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            if (user != null) {
                val customerCollection = Firebase.firestore.collection(collectionPath = "customer")
                val customerExists = customerCollection.document(user.uid).get().exists

                if (customerExists) {
                    onSuccess()
                } else {
                    val customer = Customer(
                        id = user.uid,
                        firstName = user.displayName?.split(" ")?.firstOrNull() ?: "Unknown",
                        lastName = user.displayName?.split(" ")?.lastOrNull() ?: "Unknown",
                        email = user.email ?: "Unknown",
                    )
                    customerCollection.document(user.uid).set(customer)
                }
            } else {
                onError("User is not available")
            }
        } catch (e: Exception) {
            onError("Error while creating a Customer: ${e.message}")
        }
    }
}