package com.sportfood.sportfood

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sportfood.shared.util.IntentHandler
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    val intentHandler: IntentHandler by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = intent.data

        println("SPORTFOOD INTENT TRIGGERED: $uri")

        val isSuccess = uri?.getQueryParameter("success")
        val isCancelled = uri?.getQueryParameter("cancel")
        val token = uri?.getQueryParameter("token")

        println("SPORTFOOD INTENT SUCCESS: $isSuccess")
        println("SPORTFOOD INTENT CANCELLED: $isCancelled")
        println("SPORTFOOD INTENT TOKEN: $token")

        intentHandler.navigateToPaymentCompleted(
            isSuccess = isSuccess?.toBooleanStrictOrNull(),
            error = if (isCancelled == "null") null else "Payment has been canceled",
            token = token
        )
    }
}