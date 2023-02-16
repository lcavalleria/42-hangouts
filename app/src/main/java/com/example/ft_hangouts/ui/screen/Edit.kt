package com.example.ft_hangouts.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ft_hangouts.MainActivity
import com.example.ft_hangouts.ui.layout.FavIndicator
import com.example.ft_hangouts.ui.layout.ProfilePic
import com.example.ft_hangouts.viewModel.Contact
import com.example.ft_hangouts.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Edit(viewModel: MainViewModel, navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        val selectedContactState = viewModel.selectedContactState.collectAsState()
        val selectedContactValue: Contact? = selectedContactState.value

        if (selectedContactValue != null) {
            if (selectedContactValue.id.isNotEmpty()) {
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
                        text = "Delete contact",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(Color.Gray, shape = RoundedCornerShape(16.dp))
                            .padding(16.dp),
                        color = Color.Red
                    )
                }
            } else Text(text = "Id is empty")
        } else Text(text = "Null contact")
    }
}