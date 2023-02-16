package com.example.ft_hangouts.ui.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.datasource.DateUtils
import com.example.ft_hangouts.ui.screen.Router
import com.example.ft_hangouts.viewModel.Contact

@Composable
fun ContactRow(
    contact: Contact,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ContactImgFav(
            contact = contact
        )
        Column(
            Modifier
                .weight(1f)
        ) {
            Text(text = contact.name)
            Text(text = DateUtils.formatTimeAgo(contact.lastOnline))
        }
    }
}

