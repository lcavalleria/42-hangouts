package com.lcavalle.ft_hangouts.ui.layout

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lcavalle.ft_hangouts.MainViewModel

@Composable
fun colorsFromStatusBar(
    viewModel: MainViewModel = viewModel(),
): TopAppBarColors {
    val activity = LocalView.current.context as Activity
    LaunchedEffect(Unit) {
        viewModel.setStatusBarColor(Color(activity.window.statusBarColor))
    }
    val statusColorState = viewModel.statusBarColorState.collectAsState()
    val statusColor = statusColorState.value
    val isBright = statusColor.luminance() > 0.3f
    val contentsColor = if (isBright) Color.Black else Color.White
    return TopAppBarDefaults.smallTopAppBarColors(
        containerColor = statusColor,
        titleContentColor = contentsColor,
        navigationIconContentColor = contentsColor,
        scrolledContainerColor = contentsColor,
        actionIconContentColor = contentsColor
    )
}