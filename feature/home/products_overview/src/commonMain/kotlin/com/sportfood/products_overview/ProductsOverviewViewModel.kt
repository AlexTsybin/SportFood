package com.sportfood.products_overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sportfood.data.domain.ProductRepository
import com.sportfood.shared.util.RequestState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn

class ProductsOverviewViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val newProducts = productRepository.readNewProductsFlow()
    private val discountedProducts = productRepository.readDiscountedProductsFlow()

    init {
        newProducts.combine(discountedProducts) { new, discounted ->

        }.launchIn(viewModelScope)
    }

    val products = combine(
        newProducts,
        discountedProducts
    ) { new, discounted ->
        when {
            new.isSuccess() && discounted.isSuccess() -> {
                RequestState.Success(new.getSuccessData() + discounted.getSuccessData())
            }
            new.isError() -> new
            discounted.isError() -> discounted
            else -> RequestState.Loading
         }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RequestState.Loading
    )
}