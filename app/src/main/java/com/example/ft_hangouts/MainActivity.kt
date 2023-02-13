package com.example.ft_hangouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ft_hangouts.datasource.Contact
import com.example.ft_hangouts.ui.layout.ContactRow
import com.example.ft_hangouts.ui.layout.Home
import com.example.ft_hangouts.ui.theme.FthangoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FthangoutsTheme {
                Home()
            }
        }
    }
}
