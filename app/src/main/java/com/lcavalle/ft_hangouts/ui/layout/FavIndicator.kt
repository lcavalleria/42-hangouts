package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize

@Composable
fun FavIndicator(isFav: Boolean, size: DpSize, onClick: (() -> Unit)? = null) {
    val imageVector =
        if (isFav) Icons.Rounded.Favorite
        else Icons.Rounded.Star
    Icon(
        imageVector,
        contentDescription = "Favorite icon.",
        modifier = Modifier
            .size(size)
            .clickable(enabled = onClick != null) { onClick?.invoke() }
    )
}