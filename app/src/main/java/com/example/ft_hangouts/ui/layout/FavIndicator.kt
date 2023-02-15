package com.example.ft_hangouts.ui.layout

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavIndicator(isFav: Boolean, modifier: Modifier) {
    val imageVector = if (isFav) Icons.Rounded.Favorite
    else Icons.Outlined.Favorite
    Icon(
        imageVector,
        contentDescription = "Favorite icon.",
        modifier = modifier
            .size(19.dp)
    )
}