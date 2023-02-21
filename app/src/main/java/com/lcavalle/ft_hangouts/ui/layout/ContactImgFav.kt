package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.lcavalle.ft_hangouts.Contact

@Composable
fun ContactImgFav(
    contact: Contact,
    bitmap: ImageBitmap?,
    modifier: Modifier = Modifier,
    onProfilePicClick: (() -> Unit)? = null,
    onFavClick: (() -> Unit)? = null,
) {
    Box(modifier = modifier) {
        ProfilePic(
            contact = contact,
            bitmap = bitmap,
            size = DpSize(height = 50.dp, width = 50.dp),
            onClick = onProfilePicClick
        )
        FavIndicator(
            tint = if (contact.isFavorite) Color.Yellow else Color.Transparent,
            size = DpSize(24.dp, 24.dp),
            onClick = onFavClick,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset((-24).dp, (0).dp)
        )
    }
}