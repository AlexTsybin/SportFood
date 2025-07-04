package com.sportfood.shared.util

import com.sportfood.shared.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// It doesn't work for iOS properly
class IntentHandler {

    private val _navigateTo = MutableStateFlow<Screen?>(null)
    val navigateTo = _navigateTo.asStateFlow()

    fun navigateToPaymentCompleted(
        isSuccess: Boolean?,
        error: String?,
        token: String?,
    ) {
        _navigateTo.value = Screen.PaymentCompleted(
            isSuccess = isSuccess,
            error = error,
            token = token
        )
    }

    fun resetNavigation() {
        _navigateTo.value = null
    }
}