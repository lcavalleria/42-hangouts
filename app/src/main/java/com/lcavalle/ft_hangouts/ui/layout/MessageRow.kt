package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lcavalle.ft_hangouts.datasource.DateUtils

@Composable
fun MessageRow(timestamp: Long, isMine: Boolean, message: String) {
    val arrangement =
        if (isMine) Arrangement.End
        else Arrangement.Start
    Column(Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            textAlign = TextAlign.Center,
            text = DateUtils.formatRelativeDateTime(timestamp, LocalConfiguration.current)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = arrangement
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp),
                text = message
            )
        }
    }
}