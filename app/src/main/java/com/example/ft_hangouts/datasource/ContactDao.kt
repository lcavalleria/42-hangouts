package com.example.ft_hangouts.datasource

import androidx.compose.ui.graphics.ImageBitmap
import java.time.Instant

data class ContactDao(
    val name: String,
    val number: String,
    val mail: String,
    val picture: ImageBitmap,
    val isFavorite: Boolean,
    val lastOnline: Instant
)

