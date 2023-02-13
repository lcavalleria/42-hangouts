package com.example.ft_hangouts.ui.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ft_hangouts.datasource.Contact

@Composable
fun Home() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(Contact.placeholder) { contact ->
            ContactRow(contact = contact)
        }
    }
}