package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun FtHangoutsTopBar(
    left: @Composable () -> Unit,
    center: @Composable () -> Unit,
    right: @Composable () -> Unit,
) {
    SmallTopAppBar(title = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            fun withWeight(weight: Float): Modifier {
                return Modifier
                    .fillMaxSize()
                    .weight(weight)
            }

            Box(withWeight(0.2f)) { Row(modifier = Modifier.align(Alignment.CenterStart)) { left() } }
            Box(withWeight(0.6f)) { Row(modifier = Modifier.align(Alignment.Center)) { center() } }
            Box(withWeight(0.2f)) { Row(modifier = Modifier.align(Alignment.CenterEnd)) { right() } }
        }
    })
}