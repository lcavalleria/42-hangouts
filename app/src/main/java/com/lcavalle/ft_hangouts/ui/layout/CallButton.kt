package com.lcavalle.ft_hangouts.ui.layout

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lcavalle.ft_hangouts.viewModel.Contact

@Composable
fun CallButton(contact: Contact, context: Context) {
    val activity = context as Activity
    IconButton(
        onClick = {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:" + contact.number)
            if (ContextCompat.checkSelfPermission(
                    activity, android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity, arrayOf(android.Manifest.permission.CALL_PHONE), 0
                )
            } else if (ContextCompat.checkSelfPermission(
                    activity, android.Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is granted
                activity.startActivity(callIntent)
            } else {
                Toast.makeText(
                    activity, "Can't call if you don't give call permission!", Toast.LENGTH_SHORT
                ).show()
            }
        },
        Modifier
            .padding(horizontal = 8.dp)
            .background(Color.Gray, RoundedCornerShape(16.dp))
            .size(width = 112.dp, height = 80.dp)
    ) {
        Icon(Icons.Rounded.Call, modifier = Modifier.size(40.dp), contentDescription = "Call")
    }
}