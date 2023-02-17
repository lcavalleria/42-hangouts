package com.lcavalle.ft_hangouts.ui.chat

import android.telephony.SmsManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lcavalle.ft_hangouts.ui.layout.MessageRow

@Composable
fun Chat(
    id: String,
    viewModel: ChatViewModel = viewModel()
) {
    val context = LocalContext.current
    val currentMessage by viewModel.getCurrentMessage().collectAsState()
    val messages = viewModel.getMessageHistoryById(id)
        .collectAsState().value.toList()
    val contact by viewModel.getContactById(id).collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(messages) { message: Pair<Long, Pair<Boolean, String>> ->
                MessageRow(message.first, message.second.first, message.second.second)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red), horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = currentMessage,
                shape = RoundedCornerShape(16.dp),
                onValueChange = { viewModel.setCurrentMessage(it) })
            IconButton(modifier = Modifier
                .background(Color.Gray, shape = Shapes.Full),
                onClick = {
                    val smsManager: SmsManager = context.getSystemService(SmsManager::class.java)
                    smsManager.sendTextMessage(contact.number, null, currentMessage, null, null)
                }) {
                Icon(Icons.Rounded.Send, "Send")
            }
        }
    }
}