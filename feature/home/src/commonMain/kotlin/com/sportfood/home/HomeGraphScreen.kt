package com.sportfood.home

import ContentWithMessageBar
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sportfood.shared.Alpha
import com.sportfood.shared.BebasNeueFont
import com.sportfood.shared.FontSize
import com.sportfood.shared.IconPrimary
import com.sportfood.shared.Resources
import com.sportfood.shared.Surface
import com.sportfood.shared.SurfaceLighter
import com.sportfood.shared.TextPrimary
import com.sportfood.shared.navigation.Screen
import com.sportfood.shared.util.getScreenWidth
import com.sportfood.home.component.BottomBar
import com.sportfood.home.component.CustomDrawer
import com.sportfood.home.domain.BottomBarDestination
import com.sportfood.home.domain.CustomDrawerState
import com.sportfood.home.domain.isOpened
import com.sportfood.home.domain.opposite
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeGraphScreen(
    navigateToAuth: () -> Unit,
) {
    val viewModel = koinViewModel<HomeGraphViewModel>()
    val messageBarState = rememberMessageBarState()
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState()
    val selectedDestination by remember {
        derivedStateOf {
            val route = currentRoute.value?.destination?.route.toString()
            when {
                route.contains(BottomBarDestination.Products.screen.toString()) -> BottomBarDestination.Products
                route.contains(BottomBarDestination.Cart.screen.toString()) -> BottomBarDestination.Cart
                route.contains(BottomBarDestination.Categories.screen.toString()) -> BottomBarDestination.Categories
                else -> BottomBarDestination.Products
            }
        }
    }
    val screenWidth = remember { getScreenWidth() }
    var drawerState by remember { mutableStateOf(CustomDrawerState.Closed) }
    val offsetValue by remember { derivedStateOf { (screenWidth * 0.6).dp } }
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.9f else 1f
    )
    val animatedRadius by animateDpAsState(
        targetValue = if (drawerState.isOpened()) 20.dp else 0.dp
    )
    val animatedBackground by animateColorAsState(
        targetValue = if (drawerState.isOpened()) SurfaceLighter else Surface
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedBackground)
            .systemBarsPadding()
    ) {
        CustomDrawer(
            onProfileClick = {},
            onContactUsClick = {},
            onSignOutCLick = {
                viewModel.signOut(
                    onSuccess = navigateToAuth,
                    onError = { message -> messageBarState.addError(message = message) }
                )
            },
            onAdminPanelClick = {}
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(size = animatedRadius))
                .offset(x = animatedOffset)
                .scale(scale = animatedScale)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(size = animatedRadius),
                    ambientColor = Color.Black.copy(alpha = Alpha.DISABLED),
                    spotColor = Color.Black.copy(alpha = Alpha.DISABLED)
                )
        ) {
            Scaffold(
                containerColor = Surface,
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            AnimatedContent(
                                targetState = selectedDestination
                            ) { destination ->
                                Text(
                                    text = destination.title,
                                    fontFamily = BebasNeueFont(),
                                    fontSize = FontSize.LARGE,
                                    color = TextPrimary
                                )
                            }
                        },
                        navigationIcon = {
                            AnimatedContent(
                                targetState = drawerState
                            ) { drawer ->
                                if (drawer.isOpened()) {
                                    IconButton(onClick = { drawerState = drawerState.opposite() }) {
                                        Icon(
                                            painter = painterResource(Resources.Icon.Close),
                                            contentDescription = "Close icon",
                                            tint = IconPrimary
                                        )
                                    }
                                } else {
                                    IconButton(onClick = { drawerState = drawerState.opposite() }) {
                                        Icon(
                                            painter = painterResource(Resources.Icon.Menu),
                                            contentDescription = "Menu icon",
                                            tint = IconPrimary
                                        )
                                    }
                                }
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Surface,
                            scrolledContainerColor = Surface,
                            navigationIconContentColor = IconPrimary,
                            titleContentColor = TextPrimary,
                            actionIconContentColor = IconPrimary
                        )
                    )
                }
            ) { padding ->
                ContentWithMessageBar(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = padding.calculateTopPadding(),
                            bottom = padding.calculateBottomPadding()
                        ),
                    messageBarState = messageBarState,
                    errorMaxLines = 2,
                    contentBackgroundColor = Surface
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        NavHost(
                            modifier = Modifier.weight(1f),
                            navController = navController,
                            startDestination = Screen.Products
                        ) {
                            composable<Screen.Products> { Text("Products") }
                            composable<Screen.Cart> { Text("Cart") }
                            composable<Screen.Categories> { Text("Categories") }
                        }
                        Spacer(modifier = Modifier.height(12.dp ))
                        Box(
                            modifier = Modifier
                                .padding(all = 12.dp)
                        ) {
                            BottomBar(
                                selected = selectedDestination,
                                onSelect = { destination ->
                                    navController.navigate(destination.screen) {
                                        launchSingleTop = true
                                        popUpTo<Screen.Products> {
                                            saveState = true
                                            inclusive = false
                                        }
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}