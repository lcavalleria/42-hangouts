package com.example.ft_hangouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgs
import androidx.navigation.navArgument
import com.example.ft_hangouts.repository.ContactsRepository
import com.example.ft_hangouts.ui.screen.Edit
import com.example.ft_hangouts.ui.screen.Home
import com.example.ft_hangouts.ui.screen.Router
import com.example.ft_hangouts.ui.theme.FthangoutsTheme
import com.example.ft_hangouts.viewModel.Contact
import com.example.ft_hangouts.viewModel.MainViewModel

class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val viewModel = viewModel<MainViewModel>()
            val contactsState by viewModel.contactsState.collectAsState()
            viewModel.loadContacts(ContactsRepository.fetchContacts())
            FthangoutsTheme {
                //TOdo: GUARDAR A VIEWMODEL? si minimitzo la app, tanco desde android studio, i desminimitzo, torna a Home.
                NavHost(navController = navController, startDestination = Router.Home) {
                    composable(route = Router.Home) {
                        Home(
                            contactsState.values.toList(),
                            viewModel,
                            navController
                        )
                    }
                    composable(route = Router.Edit) { Edit(viewModel, navController) }
                }
            }
        }
    }
}
