package com.alextsy.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.Font
import sportfood.shared.generated.resources.Res
import sportfood.shared.generated.resources.bebas_neue_regular
import sportfood.shared.generated.resources.roboto_condensed_medium

@Composable
fun BebasNeueFont() = FontFamily(
    Font(Res.font.bebas_neue_regular)
)

@Composable
fun RobotoCondensedFont() = FontFamily(
    Font(Res.font.roboto_condensed_medium)
)

object FontSize {
    const val EXTRA_SMALL = 10
    const val SMALL = 12
    const val REGULAR = 14
    const val EXTRA_REGULAR = 16
    const val MEDIUM = 18
    const val EXTRA_MEDIUM = 20
    const val LARGE = 30
    const val EXTRA_LARGE = 40
}