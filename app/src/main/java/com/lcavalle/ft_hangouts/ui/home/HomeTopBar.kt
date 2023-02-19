package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.ft_hangouts.R
import com.lcavalle.ft_hangouts.Router


@Composable
fun HomeTopBar(navController: NavController) {
    FtHangoutsTopBar(
        left = {},
        center = {
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
            )
        },
        right = {
            IconButton(
                onClick = { navController.navigate(Router.Settings.route) }
            ) {
                Icon(Icons.Rounded.Settings, "Settings")
            }
        }
    )
}