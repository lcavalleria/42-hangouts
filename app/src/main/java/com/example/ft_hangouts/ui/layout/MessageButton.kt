package com.example.ft_hangouts.ui.layout

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.viewModel.Contact

@Composable
fun MessageButton(contact: Contact, context: Context) {
    IconButton(
        onClick = {
            val smsIntent = Intent(Intent.ACTION_SENDTO)
            smsIntent.data = Uri.fromParts("smsto", contact.number, null)
            smsIntent.putExtra("sms_body", "TODO: Text from viewmodel state.")
            context.startActivity(smsIntent)
        },
        Modifier
            .padding(horizontal = 8.dp)
            .background(Color.Gray, RoundedCornerShape(16.dp))
            .size(width = 112.dp, height = 80.dp)
    ) {
        Icon(Icons.Rounded.Chat, modifier = Modifier.size(40.dp), contentDescription = "Message")
    }
}
