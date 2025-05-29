package com.sportfood.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sportfood.shared.Surface
import com.sportfood.shared.component.ProfileForm

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .background(Surface)
            .systemBarsPadding()
    ) {
        ProfileForm(
            firstName = "",
            onFirstNameChange = {},
            lastName = "Tsybin",
            onLastNameChange = {},
            email = "",
            city = "",
            onCityChange = {},
            postalCode = null,
            onPostalCodeChange = {},
            address = "",
            onAddressChange = {},
            phoneNumber = null,
            onPhoneNumberChange = {}
        )
    }
}