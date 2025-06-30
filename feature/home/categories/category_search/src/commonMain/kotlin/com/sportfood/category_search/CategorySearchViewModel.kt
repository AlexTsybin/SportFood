package com.sportfood.category_search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sportfood.data.domain.ProductRepository
import com.sportfood.shared.domain.product.ProductCategory
import com.sportfood.shared.util.RequestState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class CategorySearchViewModel(
    private val productRepository: ProductRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val products = productRepository.readProductsByCategoryFlow(
        category = ProductCategory.valueOf(
            savedStateHandle.get<String>("category") ?: ProductCategory.Protein.name
        )
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RequestState.Loading
    )
}