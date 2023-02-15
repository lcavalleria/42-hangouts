package com.example.ft_hangouts.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.viewModel.Contact

@Composable
fun ContactImgFav(
    contact: Contact,
    picModifier: Modifier = Modifier,
    favModifier: Modifier = Modifier
) {
    Box {
        ProfilePic(
            contact = contact,
            picModifier
        )
        FavIndicator(
            contact.isFavorite,
            favModifier
                .size(19.dp)
                .offset(x = (-8).dp, y = (-7).dp)
                .align(Alignment.TopStart)
        )
    }
}