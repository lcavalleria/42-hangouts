package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lcavalle.ft_hangouts.viewModel.Contact

@Composable
fun MessageButton(contact: Contact, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        Modifier
            .padding(horizontal = 8.dp)
            .background(Color.Gray, RoundedCornerShape(16.dp))
            .size(width = 112.dp, height = 80.dp)
    ) {
        Icon(Icons.Rounded.Chat, modifier = Modifier.size(40.dp), contentDescription = "Message")
    }
}
