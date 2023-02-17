package com.lcavalle.ft_hangouts.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lcavalle.ft_hangouts.Router
import com.lcavalle.ft_hangouts.ui.layout.CallButton
import com.lcavalle.ft_hangouts.ui.layout.EditButton
import com.lcavalle.ft_hangouts.ui.layout.MessageButton
import com.lcavalle.ft_hangouts.ui.layout.ProfilePic
import com.lcavalle.ft_hangouts.viewModel.Contact

@Composable
fun Details(
    navController: NavController,
    id: String,
    viewModel: DetailsViewModel = viewModel(),
) {
    val selectedContactState = viewModel.getContactById(id).collectAsState()
    val selectedContactValue: Contact = selectedContactState.value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        ProfilePic(contact = selectedContactValue, size = DpSize(width = 160.dp, 160.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            CallButton(contact = selectedContactValue, context = context)
            MessageButton(contact = selectedContactValue, onClick = {
                navController.navigate(Router.Chat.withId(selectedContactValue.id))
            })
            EditButton {
                navController.navigate(Router.Edit.withId(id))
            }
        }
        Text(text = selectedContactValue.name)
        Text(text = selectedContactValue.number)
        Text(text = selectedContactValue.mail)
    }
}