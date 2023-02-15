package com.example.ft_hangouts.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.ft_hangouts.ui.layout.ContactRow
import com.example.ft_hangouts.viewModel.Contact
import com.example.ft_hangouts.viewModel.MainViewModel


@Composable
fun Home(
    contacts: List<Contact>,
    viewModel: MainViewModel,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(contacts) {
            ContactRow(contact = it, viewModel, navController)
        }
    }
}