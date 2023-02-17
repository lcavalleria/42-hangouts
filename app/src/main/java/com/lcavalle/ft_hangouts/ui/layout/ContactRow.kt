package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.lcavalle.ft_hangouts.datasource.DateUtils
import com.lcavalle.ft_hangouts.viewModel.Contact

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
            Text(
                text = DateUtils.formatRelativeDateTime(contact.lastOnline, LocalConfiguration.current)
            )
        }
    }
}

