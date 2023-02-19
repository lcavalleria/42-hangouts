package com.lcavalle.ft_hangouts.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.R
import com.lcavalle.ft_hangouts.Contact
import com.lcavalle.ft_hangouts.Router
import com.lcavalle.ft_hangouts.ui.layout.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun Details(
    navController: NavController,
    id: Long,
    viewModel: DetailsViewModel = viewModel(),
) {
    viewModel.selectContactById(id)
    val contactState = viewModel.selectedContactState.collectAsState()
    val contact: Contact = contactState.value
    Scaffold(
        topBar = {
            FtHangoutsTopBar(
                left = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
                center = { Text(stringResource(id = R.string.contact_details)) },
                right = {}
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val context = LocalContext.current
                ProfilePic(contact = contact, size = DpSize(width = 160.dp, 160.dp))
                Row(horizontalArrangement = Arrangement.Center) {
                    CallButton(contact = contact, context = context)
                    MessageButton(contact = contact, onClick = {
                        navController.navigate(Router.Chat.withId(contact.id))
                    })
                    EditButton {
                        navController.navigate(Router.Edit.withId(id))
                    }
                }
                Text(text = contact.name)
                Text(text = contact.number)
                Text(text = contact.mail)
            }
        }
    )
}