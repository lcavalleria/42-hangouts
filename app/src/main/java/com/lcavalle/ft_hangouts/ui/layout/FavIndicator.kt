package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize

@Composable
fun FavIndicator(
    tint: Color,
    size: DpSize,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Icon(
        Icons.Rounded.Star,
        contentDescription = "Favorite icon.",
        modifier = modifier
            .size(size)
            .clickable(
                enabled = onClick != null,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick?.invoke() },
        tint = tint
    )
}