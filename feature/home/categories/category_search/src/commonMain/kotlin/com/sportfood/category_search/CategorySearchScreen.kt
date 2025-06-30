package com.sportfood.category_search

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sportfood.shared.BebasNeueFont
import com.sportfood.shared.FontSize
import com.sportfood.shared.IconPrimary
import com.sportfood.shared.Resources
import com.sportfood.shared.Surface
import com.sportfood.shared.TextPrimary
import com.sportfood.shared.component.InfoCard
import com.sportfood.shared.component.LoadingCard
import com.sportfood.shared.component.ProductCard
import com.sportfood.shared.domain.product.ProductCategory
import com.sportfood.shared.util.DisplayResult
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySearchScreen(
    modifier: Modifier = Modifier,
    category: ProductCategory,
    navigateToDetails: (String) -> Unit,
    navigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<CategorySearchViewModel>()
    val products by viewModel.products.collectAsState()

    Scaffold(
        containerColor = Surface,
        topBar = {
            AnimatedContent(
                targetState = false
            ) { visible ->
                if (visible) {
//                    SearchBar(
//                        modifier = Modifier
//                            .padding(horizontal = 12.dp)
//                            .fillMaxWidth(),
//                        inputField = {
//                            SearchBarDefaults.InputField(
//                                modifier = Modifier.fillMaxWidth(),
//                                query = searchQuery,
//                                onQueryChange = viewModel::updateSearchQuery,
//                                expanded = false,
//                                onExpandedChange = {},
//                                onSearch = {},
//                                placeholder = {
//                                    Text(
//                                        text = "Search here",
//                                        fontSize = FontSize.REGULAR,
//                                        color = TextPrimary
//                                    )
//                                },
//                                trailingIcon = {
//                                    IconButton(
//                                        modifier = Modifier.size(14.dp),
//                                        onClick = {
//                                            if (searchQuery.isNotEmpty()) {
//                                                viewModel.updateSearchQuery("")
//                                            } else {
//                                                searchBarVisible = false
//                                            }
//                                        }
//                                    ) {
//                                        Icon(
//                                            painter = painterResource(Resources.Icon.Close),
//                                            contentDescription = "Close icon",
//                                            tint = IconPrimary
//                                        )
//                                    }
//                                }
//                            )
//                        },
//                        colors = SearchBarColors(
//                            containerColor = SurfaceLighter,
//                            dividerColor = BorderIdle
//                        ),
//                        expanded = false,
//                        onExpandedChange = {},
//                        content = {}
//                    )
                } else {
                    TopAppBar(
                        title = {
                            Text(
                                text = category.title,
                                fontFamily = BebasNeueFont(),
                                fontSize = FontSize.LARGE,
                                color = TextPrimary
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = navigateBack) {
                                Icon(
                                    painter = painterResource(Resources.Icon.BackArrow),
                                    contentDescription = "Back Arrow icon",
                                    tint = IconPrimary
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {
//                                searchBarVisible = true
                            }) {
                                Icon(
                                    painter = painterResource(Resources.Icon.Search),
                                    contentDescription = "Search icon",
                                    tint = IconPrimary
                                )
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
            }
        }
    ) { padding ->
        products.DisplayResult(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
            onLoading = { LoadingCard(modifier = Modifier.fillMaxSize()) },
            onSuccess = { categoryProducts ->
                AnimatedContent(
                    targetState = categoryProducts
                ) { products ->
                    if (products.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(
                                items = products,
                                key = { it.id }
                            ) { product ->
                                ProductCard(
                                    product = product,
                                    onClick = navigateToDetails
                                )
                            }
                        }
                    } else {
                        InfoCard(
                            image = Resources.Image.Cat,
                            title = "Nothing here",
                            subtitle = "We couldn't find any product"
                        )
                    }
                }
            },
            onError = { message ->
                InfoCard(
                    image = Resources.Image.Cat,
                    title = "Oops!",
                    subtitle = message
                )
            }
        )
    }
}