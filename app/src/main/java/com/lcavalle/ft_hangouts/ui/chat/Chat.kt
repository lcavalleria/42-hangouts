package com.lcavalle.ft_hangouts.ui.chat

import android.app.Activity
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lcavalle.ft_hangouts.datasource.sms.SmsMessageDto
import com.lcavalle.ft_hangouts.ui.layout.MessageRow
import com.lcavalle.ft_hangouts.ui.layout.colorsFromStatusBar


fun hasSmsPermission(activity: Activity): Boolean {
    return ContextCompat.checkSelfPermission(
        activity,
        android.Manifest.permission.SEND_SMS
    ) == PackageManager.PERMISSION_GRANTED
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chat(id: Long, navController: NavController, viewModel: ChatViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.selectContactById(id)
    }
    val contactState = viewModel.selectedContact.collectAsState()
    val contact = contactState.value
    val currentMessage by viewModel.getCurrentMessage().collectAsState()
    val messages = viewModel.getMessageHistoryById(contact.id)
        .collectAsState().value.toList()

    val activity = LocalContext.current as Activity
    val smsManager: SmsManager = activity.getSystemService(SmsManager::class.java)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = colorsFromStatusBar(),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
                title = { Text(text = if (contact.name.length > 17) contact.name.take(19) + "..." else contact.name) }
            )
        }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                reverseLayout = true
            ) {
                items(messages) { message: SmsMessageDto ->
                    MessageRow(message.timestamp, message.isFromUser, message.content)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(Shapes.Full)
                        .height(54.dp),
                    value = currentMessage,
                    shape = Shapes.Full,
                    onValueChange = { viewModel.setCurrentMessage(it) })
                IconButton(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, shape = Shapes.Full)
                        .size(48.dp, 48.dp),
                    onClick = {
                        if (hasSmsPermission(activity)) {
                            if (currentMessage.isNotBlank()) {
                                /*
                                val sentPi: PendingIntent = PendingIntent.getBroadcast(
                                    activity,
                                    RequestCode.SendSms,
                                    Intent(FthSentSmsReceiver.IntentSent),
                                    PendingIntent.FLAG_IMMUTABLE
                                )
                                 */
                                smsManager.sendTextMessage(
                                    contact.number,
                                    null,
                                    currentMessage,
                                    null,
                                    null
                                )
                                viewModel.storeMessage(
                                    SmsMessageDto(
                                        id = 0,// assigned by Room
                                        contactId = contact.id,
                                        timestamp = System.currentTimeMillis(),
                                        isFromUser = true,
                                        content = currentMessage
                                    )
                                )
                                viewModel.setCurrentMessage("")
                            }
                        } else {
                            Toast.makeText(
                                activity,
                                "Can't send sms if you don't give permission!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                    Icon(Icons.Rounded.Send, "Send")
                }
            }
        }
    }
}
