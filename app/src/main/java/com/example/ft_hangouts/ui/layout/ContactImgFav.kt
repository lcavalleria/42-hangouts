package com.example.ft_hangouts.ui.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.viewModel.Contact

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