package com.example.ft_hangouts.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.ft_hangouts.MainActivity
import com.example.ft_hangouts.ui.layout.ContactImgFav
import com.example.ft_hangouts.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Edit(viewModel: MainViewModel, navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        val editContactState by viewModel.editingContactState.collectAsState()
        if (editContactState != null) {
            if (editContactState!!.id.isNotEmpty()) {
                Row() {
                    Text(text = "go back", Modifier
                        .clickable {
                            navController.navigate(Router.Home)
                        })
                }
                Row(horizontalArrangement = Arrangement.Center) {
                    TextField(
                        value = editContactState!!.name,
                        maxLines = 1,
                        placeholder = { Text("Name") },
                        onValueChange = { viewModel.setEditContact(editContactState!!.copy(name = it)) } // TODO: AIXO ESTA BE? O QUE
                    )
                }
                Row(horizontalArrangement = Arrangement.Center) {
                    TextField(
                        value = editContactState!!.number,
                        maxLines = 1,
                        placeholder = { Text("Number") },
                        onValueChange = { viewModel.setEditContact(editContactState!!.copy(number = it)) } // TODO: AIXO ESTA BE? O QUE
                    )
                }
                Row(horizontalArrangement = Arrangement.Center) {
                    TextField(
                        value = editContactState!!.mail,
                        maxLines = 1,
                        placeholder = { Text("Number") },
                        onValueChange = { viewModel.setEditContact(editContactState!!.copy(mail = it)) } // TODO: AIXO ESTA BE? O QUE
                    )
                }
                Row(horizontalArrangement = Arrangement.Center) {
                    ContactImgFav(
                        contact = editContactState!!,
                        picModifier = Modifier.clickable { navController.navigate(Router.PictureSelector) },
                        favModifier = Modifier
                            .clickable {
                                viewModel.setEditContact(editContactState!!.copy(isFavorite = !editContactState!!.isFavorite))
                                Log.d(
                                    MainActivity.TAG,
                                    "fav changed: ${editContactState!!.isFavorite}" //TODO: AIXO CANVIA PERO EL DIBUIXET NO!!
                                )
                            }
                    )
                }
            } else Text(text = "Id is empty")
        } else Text(text = "Null contact")
    }
}