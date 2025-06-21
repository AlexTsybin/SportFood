package com.sportfood.shared.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sportfood.shared.FontSize
import com.sportfood.shared.IconPrimary
import com.sportfood.shared.Resources
import com.sportfood.shared.SurfaceBrand
import com.sportfood.shared.SurfaceLighter
import com.sportfood.shared.TextPrimary
import com.sportfood.shared.domain.QuantityCounterSize
import org.jetbrains.compose.resources.painterResource

@Composable
fun QuantityCounter(
    modifier: Modifier = Modifier,
    size: QuantityCounterSize,
    value: String,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(size.spacing)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(size = 6.dp))
                .background(SurfaceBrand)
                .clickable { onMinusClick() }
                .padding(all = size.padding),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(14.dp),
                painter = painterResource(Resources.Icon.Minus),
                contentDescription = "Minus icon",
                tint = IconPrimary
            )
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(size = 6.dp))
                .background(SurfaceLighter)
                .padding(all = size.padding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "x$value",
                fontSize = FontSize.SMALL,
                lineHeight = FontSize.SMALL * 1.3,
                fontWeight = FontWeight.Medium,
                color = TextPrimary
            )
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(size = 6.dp))
                .background(SurfaceBrand)
                .clickable { onPlusClick() }
                .padding(all = size.padding),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(14.dp),
                painter = painterResource(Resources.Icon.Plus),
                contentDescription = "Plus icon",
                tint = IconPrimary
            )
        }
    }
}