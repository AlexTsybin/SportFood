package com.sportfood.di

import com.sportfood.admin_panel.AdminPanelViewModel
import com.sportfood.auth.AuthViewModel
import com.sportfood.cart.CartViewModel
import com.sportfood.category_search.CategorySearchViewModel
import com.sportfood.checkout.CheckoutViewModel
import com.sportfood.checkout.domain.PaypalApi
import com.sportfood.data.AdminRepositoryImpl
import com.sportfood.data.CustomerRepositoryImpl
import com.sportfood.data.OrderRepositoryImpl
import com.sportfood.data.ProductRepositoryImpl
import com.sportfood.data.domain.AdminRepository
import com.sportfood.data.domain.CustomerRepository
import com.sportfood.data.domain.OrderRepository
import com.sportfood.data.domain.ProductRepository
import com.sportfood.details.DetailsViewModel
import com.sportfood.home.HomeGraphViewModel
import com.sportfood.manage_product.ManageProductViewModel
import com.sportfood.products_overview.ProductsOverviewViewModel
import com.sportfood.profile.ProfileViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<CustomerRepository> { CustomerRepositoryImpl() } 
    single<AdminRepository> { AdminRepositoryImpl() }
    single<ProductRepository> { ProductRepositoryImpl() }
    single<OrderRepository> { OrderRepositoryImpl(get()) }
    single<PaypalApi> { PaypalApi() }
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeGraphViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::AdminPanelViewModel)
    viewModelOf(::ManageProductViewModel)
    viewModelOf(::ProductsOverviewViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::CartViewModel)
    viewModelOf(::CategorySearchViewModel)
    viewModelOf(::CheckoutViewModel)
}

expect val targetModule: Module

fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null,
) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, targetModule)
    }
}