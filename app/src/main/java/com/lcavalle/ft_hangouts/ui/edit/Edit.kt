package com.lcavalle.ft_hangouts.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.lcavalle.ft_hangouts.ui.layout.FavIndicator
import com.lcavalle.ft_hangouts.ui.layout.ProfilePic
import com.lcavalle.ft_hangouts.viewModel.Contact

@Composable
fun Edit(
    navController: NavController,
    id: String,
    viewModel: EditViewModel = viewModel()
) {
    val selectedContactState = viewModel.getContactById(id).collectAsState()
    val selectedContactValue: Contact = selectedContactState.value
    // todo: move to room
    LaunchedEffect(Unit) {
        viewModel.setEditContact(selectedContactValue)
    }
    Column(Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfilePic(
                contact = selectedContactValue,
                size = DpSize(80.dp, 80.dp),
                onClick = { navController.navigate("jajajaj") }
            )
            FavIndicator(
                isFav = selectedContactValue.isFavorite,
                size = DpSize(24.dp, 24.dp),
                onClick = { viewModel.setEditContactIsFav(!selectedContactValue.isFavorite) }
            )
            TextField(
                value = selectedContactValue.name,
                maxLines = 1,
                placeholder = { Text("Name") },
                onValueChange = { viewModel.setEditContact(selectedContactValue.copy(name = it)) }
            )
            TextField(
                value = selectedContactValue.number,
                maxLines = 1,
                placeholder = { Text("Number") },
                onValueChange = { viewModel.setEditContact(selectedContactValue.copy(number = it)) }
            )
            TextField(
                value = selectedContactValue.mail,
                maxLines = 1,
                placeholder = { Text("Mail") },
                onValueChange = { viewModel.setEditContact(selectedContactValue.copy(mail = it)) }
            )
            Text(
                text = stringResource(id = R.string.delete_contact),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                color = Color.Red
            )
        }
    }
}