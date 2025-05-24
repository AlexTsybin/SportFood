package com.alextsy.sportfood

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.alextsy.navigation.SetupNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        SetupNavGraph()
    }
}