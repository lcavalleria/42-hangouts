package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.sp
import com.lcavalle.ft_hangouts.Contact

@Composable
fun ProfilePic(
    contact: Contact,
    bitmap: ImageBitmap?,
    size: DpSize,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .size(size)
            .background(shape = Shapes.Full, color = MaterialTheme.colorScheme.primary)
            .clip(shape = Shapes.Full),
        contentAlignment = Alignment.Center
    ) {
        val isDarkTheme: Boolean = isSystemInDarkTheme()
        if (bitmap != null)
            Image(
                bitmap = bitmap,
                contentScale = ContentScale.Crop,
                contentDescription = "${contact.name}'s profile picture.",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(enabled = onClick != null) { onClick?.invoke() }
            )
        else
            Text(
                text = contact.name.getOrElse(0) { '#' }.toString(),
                fontSize = size.height.value.sp * 0.65f,
                textAlign = TextAlign.Center,
                color = if (isDarkTheme) Color.Black else Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(enabled = onClick != null) { onClick?.invoke() }
            )
    }
}