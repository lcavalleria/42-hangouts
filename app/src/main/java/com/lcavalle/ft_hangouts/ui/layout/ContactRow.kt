package com.lcavalle.ft_hangouts.ui.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lcavalle.ft_hangouts.Contact
import com.lcavalle.ft_hangouts.datasource.DateUtils

@Composable
fun ContactRow(
    contact: Contact, bitmap: ImageBitmap?, onClick: () -> Unit
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 8.dp)
            .height(72.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ContactImgFav(
            bitmap = bitmap,
            contact = contact,
            onProfilePicClick = onClick // defaults to {}, which would override this row's clickable
        )
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(start = 24.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = if (contact.name.length > 17) contact.name.take(19) + "..." else contact.name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 4.dp),
                text = DateUtils.formatRelativeDateTime(
                    contact.lastSmsReceiveTimeMs, LocalConfiguration.current
                ),
                fontSize = 14.sp,
                textAlign = TextAlign.End
            )
        }
    }
}

