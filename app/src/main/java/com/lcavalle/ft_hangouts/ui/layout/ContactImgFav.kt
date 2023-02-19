package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.lcavalle.ft_hangouts.Contact

@Composable
fun ContactImgFav(
    contact: Contact,
    modifier: Modifier = Modifier,
    onProfilePicClick: (() -> Unit)? = null,
    onFavClick: (() -> Unit)? = null,
) {
    Box(modifier = modifier) {
        ProfilePic(
            contact = contact,
            size = DpSize(height = 50.dp, width = 50.dp),
            onClick = onProfilePicClick
        )
        FavIndicator(
            isFav = contact.isFavorite,
            size = DpSize(24.dp, 24.dp),
            onClick = onFavClick
        )
    }
}