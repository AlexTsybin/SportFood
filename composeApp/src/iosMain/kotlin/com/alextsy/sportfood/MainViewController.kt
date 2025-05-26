package com.alextsy.sportfood

import androidx.compose.ui.window.ComposeUIViewController
import com.sportfood.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) { App() }