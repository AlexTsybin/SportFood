package com.sportfood.shared.component.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sportfood.shared.IconWhite
import com.sportfood.shared.Resources
import com.sportfood.shared.SurfaceLighter
import com.sportfood.shared.SurfaceSecondary
import org.jetbrains.compose.resources.painterResource

@Composable
fun Selector(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    val animatedBackground by animateColorAsState(
        targetValue = if (isSelected) SurfaceSecondary else SurfaceLighter
    )

    Box(
        modifier = modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(animatedBackground),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isSelected
        ) {
            Icon(
                modifier = Modifier.size(14.dp),
                painter = painterResource(Resources.Icon.Checkmark),
                contentDescription = "Checkmark icon",
                tint = IconWhite
            )
        }
    }
}