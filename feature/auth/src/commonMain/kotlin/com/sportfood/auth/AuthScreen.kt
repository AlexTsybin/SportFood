package com.sportfood.auth

import ContentWithMessageBar
import MessageBarState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sportfood.auth.component.GoogleButton
import com.sportfood.shared.Alpha
import com.sportfood.shared.BebasNeueFont
import com.sportfood.shared.FontSize
import com.sportfood.shared.Surface
import com.sportfood.shared.SurfaceBrand
import com.sportfood.shared.SurfaceError
import com.sportfood.shared.TextPrimary
import com.sportfood.shared.TextSecondary
import com.sportfood.shared.TextWhite
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState

@Composable
fun AuthScreen(
    navigateToHome: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val viewModel = koinViewModel<AuthViewModel>()
    val messageBarState = rememberMessageBarState()
    var loadingState by remember { mutableStateOf(false) }

    Scaffold { padding ->
        ContentWithMessageBar(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
            messageBarState = messageBarState,
            errorMaxLines = 2,
            contentBackgroundColor = Surface,
            errorContainerColor = SurfaceError,
            errorContentColor = TextWhite,
            successContainerColor = SurfaceBrand,
            successContentColor = TextPrimary
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "SPORTFOOD",
                        textAlign = TextAlign.Center,
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.EXTRA_LARGE,
                        color = TextSecondary
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(Alpha.HALF),
                        text = "Sign in to continue",
                        textAlign = TextAlign.Center,
                        fontSize = FontSize.EXTRA_REGULAR,
                        color = TextPrimary
                    )
                }
                GoogleButtonUiContainerFirebase(
                    linkAccount = false,
                    onResult = { result ->
                        result.onSuccess { user ->
                            viewModel.createCustomer(
                                user = user,
                                onSuccess = {
                                    scope.launch {
                                        signInOnSuccess(messageBarState)
                                        delay(2000)
                                        navigateToHome()
                                    }
                                },
                                onError = { message -> messageBarState.addError(message) }
                            )
                            loadingState = false
                        }.onFailure { error ->
                            signInOnFailure(error, messageBarState)
                            loadingState = false
                        }
                    }
                ) {
                    GoogleButton(
                        loading = loadingState,
                        onClick = {
                            loadingState = true
                            this@GoogleButtonUiContainerFirebase.onClick()
                        }
                    )
                }
            }
        }
    }
}

private fun signInOnSuccess(messageBarState: MessageBarState) {
    messageBarState.addSuccess("Authentication successful!")
}

private fun signInOnFailure(error: Throwable, messageBarState: MessageBarState) {
    if (error.message?.contains("A network error") == true) {
        messageBarState.addError("Internet connection unavailable")
    } else if (error.message?.contains("Idtoken is null") == true) {
        messageBarState.addError("Sign in cancelled")
    } else {
        messageBarState.addError(error.message ?: "Unknown error")
    }
}