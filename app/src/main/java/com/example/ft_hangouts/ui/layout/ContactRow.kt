package com.example.ft_hangouts.ui.layout

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.MainActivity
import com.example.ft_hangouts.datasource.DateUtils
import com.example.ft_hangouts.ui.screen.Router
import com.example.ft_hangouts.viewModel.Contact
import com.example.ft_hangouts.viewModel.MainViewModel

@Composable
fun ContactRow(
    contact: Contact,
    viewModel: MainViewModel,
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Color.Blue)
                .clickable {
                    Log.d(MainActivity.TAG, "setting edit contact in viewModel: ${contact.name}")
                    viewModel.setEditContact(contact)
                    navController.navigate(Router.Edit)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                Modifier
                    .padding(9.dp)
                    .border(1.dp, Color.Black),
            ) {
                ContactImgFav(contact = contact)
            }
            Column(
                Modifier
                    .border(2.dp, Color.Red)
                    .fillMaxWidth(0.8f)
            ) {
                Row {
                    Text(text = contact.name)
                }
                Row {
                    Text(text = DateUtils.formatTimeAgo(contact.lastOnline))
                }
            }
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .padding(vertical = 5.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val context = LocalContext.current
                    IconButton(
                        onClick = {
                            val smsIntent = Intent(Intent.ACTION_SENDTO)
                            smsIntent.data = Uri.fromParts("smsto", contact.number, null)
                            smsIntent.putExtra("sms_body", "TODO: Text from viewmodel state.")
                            context.startActivity(smsIntent)
                        },
                        Modifier
                            .background(Color.Yellow, CircleShape)
                            .size(30.dp)
                    ) {
                        Icon(Icons.Rounded.Send, contentDescription = "Message")
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                Row(
                    Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val context = LocalContext.current
                    IconButton(
                        onClick = {
                            val dialIntent = Intent(Intent.ACTION_DIAL)
                            dialIntent.data = Uri.fromParts("tel", contact.number, null)
                            context.startActivity(dialIntent)
                        },
                        Modifier
                            .background(Color.Green, CircleShape)
                            .size(30.dp)
                    ) {
                        Icon(Icons.Rounded.Call, contentDescription = "Call")
                    }
                }
            }
        }
    }
}
