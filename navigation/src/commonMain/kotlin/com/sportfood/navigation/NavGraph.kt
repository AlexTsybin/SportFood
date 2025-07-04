package com.sportfood.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sportfood.admin_panel.AdminPanelScreen
import com.sportfood.auth.AuthScreen
import com.sportfood.category_search.CategorySearchScreen
import com.sportfood.checkout.CheckoutScreen
import com.sportfood.details.DetailsScreen
import com.sportfood.shared.navigation.Screen
import com.sportfood.home.HomeGraphScreen
import com.sportfood.manage_product.ManageProductScreen
import com.sportfood.payment_completed.PaymentCompletedScreen
import com.sportfood.profile.ProfileScreen
import com.sportfood.shared.domain.product.ProductCategory
import com.sportfood.shared.util.IntentHandler
import org.koin.compose.koinInject

@Composable
fun SetupNavGraph(
    startDestination: Screen = Screen.Auth,
) {
    val navController = rememberNavController()
    val intentHandler = koinInject<IntentHandler>()
    val navigateTo by intentHandler.navigateTo.collectAsState()

    LaunchedEffect(navigateTo) {
        navigateTo?.let { paymentCompleted ->
            println("NAVIGATING TO PAYMENT COMPLETED")
            navController.navigate(paymentCompleted)
            intentHandler.resetNavigation()
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Auth> {
            AuthScreen(
                navigateToHome = {
                    navController.navigate(Screen.HomeGraph) {
                        popUpTo<Screen.Auth> { inclusive = true }
                    }
                }
            )
        }
        composable<Screen.HomeGraph> {
            HomeGraphScreen(
                navigateToAuth = {
                    navController.navigate(Screen.Auth) {
                        popUpTo<Screen.HomeGraph> { inclusive = true }
                    }
                },
                navigateToProfile = {
                    navController.navigate(Screen.Profile)
                },
                navigateToAdminPanel = {
                    navController.navigate(Screen.AdminPanel)
                },
                navigateToDetails = { productId ->
                    navController.navigate(Screen.Details(id = productId))
                },
                navigateToCategorySearch = { categoryName ->
                    navController.navigate(Screen.CategorySearch(categoryName))
                },
                navigateToCheckout = { totalAmount ->
                    navController.navigate(Screen.Checkout(totalAmount))
                }
            )
        }
        composable<Screen.Profile> {
            ProfileScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Screen.AdminPanel> {
            AdminPanelScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToManageProduct = { id ->
                    navController.navigate(Screen.ManageProduct(id = id))
                }
            )
        }
        composable<Screen.ManageProduct> {
            val id = it.toRoute<Screen.ManageProduct>().id
            ManageProductScreen(
                id = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Screen.Details> {
            DetailsScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Screen.CategorySearch> {
            val category = ProductCategory.valueOf(
                it.toRoute<Screen.CategorySearch>().category
            )
            CategorySearchScreen(
                category = category,
                navigateToDetails = { id ->
                    navController.navigate(Screen.Details(id))
                },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable<Screen.Checkout> {
            val totalAmount = it.toRoute<Screen.Checkout>().totalAmount
            CheckoutScreen(
                totalAmount = totalAmount.toDoubleOrNull() ?: 0.0,
                navigateBack = { navController.navigateUp() },
                navigateToPaymentCompleted = { isSuccess, error ->
                    navController.navigate(Screen.PaymentCompleted(isSuccess, error))
                }
            )
        }
        composable<Screen.PaymentCompleted> {
            val isSuccess = it.toRoute<Screen.PaymentCompleted>().isSuccess
            val error = it.toRoute<Screen.PaymentCompleted>().error
            PaymentCompletedScreen(
                isSuccess = isSuccess,
                error = error,
                navigateBack = {
                    navController.navigate(Screen.HomeGraph) {
                        launchSingleTop = true

                        // Clear backstack completely
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}