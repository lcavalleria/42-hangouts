package com.example.ft_hangouts.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.repository.ContactsRepository
import com.example.ft_hangouts.ui.layout.ContactRow
import com.example.ft_hangouts.ui.screen.Router


@Composable
fun Home(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController
) {
    // todo: move to room
    LaunchedEffect(Unit) {
        viewModel.loadContacts(ContactsRepository.placeholder)
    }
    val contacts by viewModel.contactsState.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(contacts) { contact ->
            ContactRow(
                contact = contact,
                onClick = {
                    navController.navigate(Router.Details.withId(contact.id))
                })
        }
    }
}