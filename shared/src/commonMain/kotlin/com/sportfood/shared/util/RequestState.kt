package com.sportfood.shared.util

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

sealed class RequestState<out T> {
    data object Idle : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
    data class Success<out T>(val data: T) : RequestState<T>()
    data class Error(val message: String) : RequestState<Nothing>()

    fun isIdle(): Boolean = this is Idle
    fun isLoading(): Boolean = this is Loading
    fun isSuccess(): Boolean = this is Success
    fun isError(): Boolean = this is Error

    fun getSuccessData() = (this as Success).data
    fun getSuccessDataOrNull() = if (this.isSuccess()) this.getSuccessData() else null
    fun getErrorMessage(): String = (this as Error).message
}

/**
 * Allow us to animate the content between different states of our [RequestState] wrapper class
 */
@Composable
fun <T> RequestState<T>.DisplayResult(
    modifier: Modifier = Modifier,
    onIdle: (@Composable () -> Unit)? = null,
    onLoading: (@Composable () -> Unit)? = null,
    onSuccess: (@Composable (T) -> Unit),
    onError: (@Composable (String) -> Unit)? = null,
    transitionSpec: ContentTransform? = scaleIn(tween(durationMillis = 400))
            + fadeIn(tween(durationMillis = 800))
            togetherWith scaleOut(tween(durationMillis = 400))
            + fadeOut(tween(durationMillis = 800)),
    backgroundColor: Color? = null,
) {
    AnimatedContent(
        modifier = Modifier
            .background(color = backgroundColor ?: Color.Unspecified),
        targetState = this,
        transitionSpec = {
            transitionSpec ?: (EnterTransition.None togetherWith ExitTransition.None)
        },
        label = "Content Animation"
    ) { state ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            when (state) {
                is RequestState.Idle -> {
                    onIdle?.invoke()
                }
                RequestState.Loading -> {
                    onLoading?.invoke()
                }
                is RequestState.Success -> {
                    onSuccess(state.getSuccessData())
                }
                is RequestState.Error -> {
                    onError?.invoke(state.getErrorMessage())
                }
            }
        }
    }
}