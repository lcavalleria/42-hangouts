package com.lcavalle.ft_hangouts.ui.home

import android.app.Activity
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.ft_hangouts.R
import kotlin.random.Random


@Composable
fun HomeTopBar() {
    val activity = LocalView.current.context as Activity

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
            )
        },
        actions = {
            SettingsDropdownMenu {
                val randomColor = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat())
                activity.window.statusBarColor = randomColor.toArgb()
                activity.window.navigationBarColor = randomColor.toArgb()
            }
        }
    )
}