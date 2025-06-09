package com.sportfood.di

import com.sportfood.auth.AuthViewModel
import com.sportfood.data.AdminRepositoryImpl
import com.sportfood.data.CustomerRepositoryImpl
import com.sportfood.data.domain.AdminRepository
import com.sportfood.data.domain.CustomerRepository
import com.sportfood.home.HomeGraphViewModel
import com.sportfood.manage_product.ManageProductViewModel
import com.sportfood.profile.ProfileViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<CustomerRepository> { CustomerRepositoryImpl() } 
    single<AdminRepository> { AdminRepositoryImpl() }
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeGraphViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::ManageProductViewModel)
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