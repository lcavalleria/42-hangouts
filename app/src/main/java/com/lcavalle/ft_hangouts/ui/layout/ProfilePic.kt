package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.lcavalle.ft_hangouts.viewModel.Contact

@Composable
fun ProfilePic(contact: Contact, size: DpSize, onClick: (() -> Unit)? = null) {
    Image(
        bitmap = contact.picture,
        contentDescription = "${contact.name}'s profile picture.",
        modifier = Modifier
            .size(size)
            .border(1.dp, Color.Green)
            .clickable(enabled = onClick != null) { onClick?.invoke() }
    )
}