package com.sportfood.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sportfood.shared.Surface
import com.sportfood.shared.component.ProfileForm
import com.sportfood.shared.domain.Country

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    var country by remember { mutableStateOf(Country.Serbia) }

    Box(
        modifier = Modifier
            .background(Surface)
            .systemBarsPadding()
    ) {
        ProfileForm(
            firstName = "",
            country = country,
            onCountrySelect = { selectedCountry ->
                country = selectedCountry
            },
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