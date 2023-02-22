package com.lcavalle.ft_hangouts.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lcavalle.ft_hangouts.Router
import com.lcavalle.ft_hangouts.ui.ProfileImageLoader
import com.lcavalle.ft_hangouts.ui.layout.ContactRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController
) {
    val contacts by viewModel.contactsState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = { HomeTopBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Router.Edit.route) }) {
                Icon(Icons.Rounded.Add, "Add")
            }
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(contacts) { contact ->
                    val bitmap = ProfileImageLoader.loadInternal(context, contact.id)
                    ContactRow(
                        contact = contact,
                        bitmap = bitmap,
                        onClick = {
                            navController.navigate(Router.Details.withId(contact.id))
                        })
                }
            }
        }
    )
}
