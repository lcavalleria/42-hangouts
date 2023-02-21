package com.lcavalle.ft_hangouts.ui.chat

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lcavalle.ft_hangouts.FthSmsReceiver
import com.lcavalle.ft_hangouts.MainActivity
import com.lcavalle.ft_hangouts.RequestCode
import com.lcavalle.ft_hangouts.datasource.sms.SmsMessageDto
import com.lcavalle.ft_hangouts.ui.layout.MessageRow


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
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
                title = { Text(text = contact.name) }
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
                    .weight(1f)
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
                        .height(48.dp),
                    value = currentMessage,
                    shape = RoundedCornerShape(24.dp),
                    onValueChange = { viewModel.setCurrentMessage(it) })
                IconButton(
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .background(Color.Gray, shape = Shapes.Full)
                        .size(48.dp, 48.dp),
                    onClick = {
                        if (!hasSmsPermission(activity)) {
                            ActivityCompat.requestPermissions(
                                activity,
                                arrayOf(android.Manifest.permission.SEND_SMS),
                                RequestCode.SendSms
                            )
                            Log.w(MainActivity.TAG, "Sms permissions not present. Requesting.")
                        } else if (hasSmsPermission(activity)) {
                            val sentPi: PendingIntent = PendingIntent.getBroadcast(
                                activity,
                                0,
                                Intent(FthSmsReceiver.IntentSent),
                                PendingIntent.FLAG_IMMUTABLE
                            )
                            smsManager.sendTextMessage(
                                contact.number,
                                null,
                                currentMessage,
                                sentPi,
                                null
                            )
                            viewModel.setCurrentMessage("")
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
