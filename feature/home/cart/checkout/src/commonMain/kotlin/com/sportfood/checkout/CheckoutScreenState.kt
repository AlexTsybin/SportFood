package com.sportfood.checkout

import com.sportfood.shared.domain.CartItem
import com.sportfood.shared.domain.Country
import com.sportfood.shared.domain.PhoneNumber

data class CheckoutScreenState(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val city: String? = null,
    val postalCode: Int? = null,
    val address: String? = null,
    val country: Country = Country.Serbia,
    val phoneNumber: PhoneNumber? = null,
    val cart: List<CartItem> = emptyList(),
)
