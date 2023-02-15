package com.example.ft_hangouts.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.viewModel.Contact

@Composable
fun ProfilePic(contact: Contact, modifier: Modifier) {
    Image(
        bitmap = contact.picture,
        contentDescription = "${contact.name}'s profile picture.",
        modifier = modifier // TODO: AIXO CAL?
            .size(49.dp)
            .border(1.dp, Color.Black)
    )
}