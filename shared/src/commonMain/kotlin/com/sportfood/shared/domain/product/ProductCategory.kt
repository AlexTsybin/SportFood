package com.sportfood.shared.domain.product

import androidx.compose.ui.graphics.Color
import com.sportfood.shared.CategoryBlue
import com.sportfood.shared.CategoryGreen
import com.sportfood.shared.CategoryPurple
import com.sportfood.shared.CategoryRed
import com.sportfood.shared.CategoryYellow

enum class ProductCategory(
    val title: String,
    val color: Color,
) {
    Protein(
        title = "Protein",
        color = CategoryYellow
    ),
    Creatine(
        title = "Creatine",
        color = CategoryBlue
    ),
    PreWorkout(
        title = "Pre-Workout",
        color = CategoryGreen
    ),
    Gainers(
        title = "Gainer",
        color = CategoryPurple
    ),
    Accessories(
        title = "Accessory",
        color = CategoryRed
    ),
}