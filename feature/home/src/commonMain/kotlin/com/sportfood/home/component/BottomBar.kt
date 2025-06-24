package com.sportfood.home.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sportfood.home.domain.BottomBarDestination
import com.sportfood.shared.IconPrimary
import com.sportfood.shared.IconSecondary
import com.sportfood.shared.SurfaceLighter
import com.sportfood.shared.domain.Customer
import com.sportfood.shared.util.RequestState
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    customer: RequestState<Customer>,
    selected: BottomBarDestination,
    onSelect: (BottomBarDestination) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 12.dp))
            .background(SurfaceLighter)
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BottomBarDestination.entries.forEach { destination ->
            val animatedTint by animateColorAsState(
                targetValue = if (selected == destination) IconSecondary else IconPrimary
            )
            Box(contentAlignment = Alignment.TopEnd) {
                IconButton(
                    onClick = { onSelect(destination) }
                ) {
                    Icon(
                        painter = painterResource(destination.icon),
                        contentDescription = "BottomBar destination icon",
                        tint = animatedTint
                    )
                }
                if (destination == BottomBarDestination.Cart) {
                    AnimatedContent(
                        targetState = customer
                    ) { customerState ->
                        if (customerState.isSuccess() && customerState.getSuccessData().cart.isNotEmpty()) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .offset(
                                        x = (-8).dp,
                                        y = (8).dp
                                    )
                                    .clip(CircleShape)
                                    .background(IconSecondary)
                            )
                        }
                    }
                }
            }
        }
    }
}