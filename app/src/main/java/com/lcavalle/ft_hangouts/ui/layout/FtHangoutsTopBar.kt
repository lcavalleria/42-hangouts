package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Deprecated("Useless class, not possible to align properly.\nJust use CenterAlignedTopAppBar.")
@Composable
fun FtHangoutsTopBar(
    left: @Composable () -> Unit,
    center: @Composable () -> Unit,
    right: @Composable () -> Unit,
) {
    SmallTopAppBar(
        navigationIcon = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            )
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                fun fillWithWeight(weight: Float): Modifier {
                    return Modifier
                        .fillMaxSize()
                        .weight(weight)
                }

                Box(
                    modifier = fillWithWeight(1f).background(Color.Red),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(modifier = Modifier.align(Alignment.CenterStart)) { left() }
                }
                Box(
                    modifier = fillWithWeight(1.4f).background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Row(modifier = Modifier.align(Alignment.Center)) { center() }
                }
                Box(
                    modifier = fillWithWeight(1f).background(Color.Blue),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) { right() }
                }
            }
        })
}