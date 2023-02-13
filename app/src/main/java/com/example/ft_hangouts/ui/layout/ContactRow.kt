package com.example.ft_hangouts.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.datasource.Contact
import com.example.ft_hangouts.datasource.DateUtils

@Composable
fun ContactRow(contact: Contact) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxSize()
            .border(2.dp, Color.Red),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start

    ) {
        Column(
            Modifier
                .padding(10.dp)
                .border(2.dp, Color.Blue),
        ) {
            Box {
                Image(
                    bitmap = contact.picture,
                    contentDescription = "${contact.name}'s profile picture.",
                    Modifier
                        .size(50.dp)
                        .border(2.dp, Color.Black)
                )
                if (contact.isFavorite) Icon(
                    bitmap = ImageBitmap.imageResource(id = com.example.ft_hangouts.R.mipmap.fav),
                    contentDescription = "Favorite icon.",
                    modifier = Modifier
                        .size(15.dp)
                        .offset(x = (-3).dp, y = 3.dp)
                        .align(Alignment.BottomStart)
                )
            }
        }
        Column(
            Modifier
                .fillMaxWidth(0.7f)
        ) {
            Row {
                Text(text = contact.name)
            }
            Row {
                Text(text = DateUtils.formatTimeAgo(contact.lastReceivedTime))
            }
        }
    }
}
