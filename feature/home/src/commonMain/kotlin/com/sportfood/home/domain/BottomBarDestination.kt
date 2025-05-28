package com.sportfood.home.domain

import com.alextsy.shared.Resources
import com.alextsy.shared.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource

enum class BottomBarDestination(
    val icon: DrawableResource,
    val title: String,
    val screen: Screen,
) {
    Products(
        icon = Resources.Icon.Home,
        title = "Sport Food",
        screen = Screen.Products
    ),
    Cart(
        icon = Resources.Icon.ShoppingCart,
        title = "Cart",
        screen = Screen.Cart
    ),
    Categories(
        icon = Resources.Icon.Categories,
        title = "Categories",
        screen = Screen.Categories
    ),
}