package com.lcavalle.ft_hangouts.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.R
import com.lcavalle.ft_hangouts.Contact
import com.lcavalle.ft_hangouts.Router
import com.lcavalle.ft_hangouts.ui.layout.FavIndicator
import com.lcavalle.ft_hangouts.ui.layout.FtHangoutsTopBar
import com.lcavalle.ft_hangouts.ui.layout.ProfilePic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Edit(
    navController: NavController, id: Long = 0, viewModel: EditViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.selectContactById(id)
    }

    val contactState = viewModel.selectedContactState.collectAsState()
    val contact: Contact = contactState.value
    Scaffold(topBar = {
        FtHangoutsTopBar(left = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Rounded.ArrowBack, "Back")
            }
        }, center = {
            Text(stringResource(id = R.string.edit_contact))
        }, right = {
            FavIndicator(
                isFav = contact.isFavorite,
                onClick = { viewModel.setEditContactIsFav(!contact.isFavorite) },
                size = DpSize(24.dp, 24.dp),
            )
            IconButton(onClick = {
                viewModel.storeEditingContact()
                navController.navigate(Router.Home.route)
            }) {
                Icon(Icons.Rounded.Done, "Save")
            }
        })
    }, content = { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfilePic(contact = contact,
                    size = DpSize(80.dp, 80.dp),
                    onClick = { navController.navigate("jajajaj") })
                TextField(value = contact.name,
                    maxLines = 1,
                    placeholder = { Text("Name") },
                    onValueChange = { viewModel.setEditContactName(it) })
                TextField(value = contact.number,
                    maxLines = 1,
                    placeholder = { Text("Number") },
                    onValueChange = { viewModel.setEditContactNumber(it) })
                TextField(value = contact.mail,
                    maxLines = 1,
                    placeholder = { Text("Mail") },
                    onValueChange = { viewModel.setEditContactMail(it) })

                val openDeleteDialog = remember { mutableStateOf(false) }
                Text(
                    text = stringResource(id = R.string.delete_contact),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(16.dp))
                        .clickable { openDeleteDialog.value = true }
                        .padding(16.dp),
                    color = Color.Red
                )
                if (openDeleteDialog.value) {
                    AlertDialog(
                        onDismissRequest = { openDeleteDialog.value = false },
                        title = { Text(stringResource(id = R.string.delete_contact_dialog_title)) },
                        dismissButton = {
                            TextButton(
                                onClick = { openDeleteDialog.value = false }) {
                                Text(text = stringResource(id = R.string.no))
                            }
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    viewModel.deleteContact()
                                    navController.navigate(Router.Home.route)
                                }) {
                                Text(text = stringResource(id = R.string.yes))
                            }
                        }
                    )
                }
            }
        }
    })
}