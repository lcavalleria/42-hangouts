package com.lcavalle.ft_hangouts.ui.edit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.R
import com.lcavalle.ft_hangouts.ui.layout.FavIndicator

@Composable
fun EditTopBar(
    isFav: Boolean,
    onNavigationIcon: () -> Unit,
    onFavClick: () -> Unit,
    onDoneClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigationIcon) {
                Icon(Icons.Rounded.ArrowBack, "Back")
            }
        },
        title = { Text(stringResource(id = R.string.edit_contact)) },
        actions = {
            FavIndicator(
                tint = if (isFav) Color.Yellow else Color.Gray,
                onClick = onFavClick,
                size = DpSize(32.dp, 32.dp)
            )
            IconButton(onClick = onDoneClick) {
                Icon(Icons.Rounded.Done, "Done")
            }
        }
    )

}