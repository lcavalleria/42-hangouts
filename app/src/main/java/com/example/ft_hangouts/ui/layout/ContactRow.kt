package com.example.ft_hangouts.ui.layout

import android.widget.Button
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.R
import com.example.ft_hangouts.datasource.Contact
import com.example.ft_hangouts.datasource.DateUtils

@Composable
fun ContactRow(contact: Contact) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Color.Blue),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                Modifier
                    .padding(10.dp)
                    .border(2.dp, Color.Black),
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
                        Icons.Rounded.Favorite,
                        contentDescription = "Favorite icon.",
                        modifier = Modifier
                            .size(20.dp)
                            .offset(x = (-7).dp, y = (-7).dp)
                            .align(Alignment.TopStart)
                    )
                }
            }
            Column(
                Modifier
                    .fillMaxWidth(0.7f)
                    .border(2.dp, Color.Red)
            ) {
                Row {
                    Text(text = contact.name)
                }
                Row {
                    Text(text = DateUtils.formatTimeAgo(contact.lastReceivedTime))
                }
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .border(2.dp, Color.Green)
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = { TODO("Message") },
                        Modifier.background(Color.Yellow, CircleShape)
                    ) {
                        Icon(Icons.Rounded.Send, contentDescription = "Message")
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                    IconButton(
                        onClick = { TODO("Call") },
                        Modifier.background(Color.Green, CircleShape)
                    ) {
                        Icon(Icons.Rounded.Call, contentDescription = "Call")
                    }
                }
            }
        }
    }
}
