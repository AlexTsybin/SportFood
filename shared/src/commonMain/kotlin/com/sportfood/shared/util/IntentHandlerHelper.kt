package com.sportfood.shared.util

import com.sportfood.shared.navigation.Screen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// It should be a workaround for iOS bu for some reason it's also doesn't work
class IntentHandlerHelper : KoinComponent {

    private val intentHandler: IntentHandler by inject()

    fun navigateToPaymentCompleted(
        isSuccess: Boolean?,
        error: String?,
        token: String?,
    ) {
        intentHandler.navigateToPaymentCompleted(
            isSuccess = isSuccess,
            error = error,
            token = token
        )
    }
}