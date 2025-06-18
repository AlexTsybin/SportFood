package com.sportfood.manage_product

import com.sportfood.shared.domain.product.ProductCategory
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)
data class ManageProductState(
    val id: String = Uuid.random().toHexString(),
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
    val title: String = "",
    val description: String = "",
    val thumbnail: String = "thumbnail image",
    val category: ProductCategory = ProductCategory.Protein,
    val price: Double = 0.0,
    val flavors: String = "",
    val weight: Int? = null,
)
