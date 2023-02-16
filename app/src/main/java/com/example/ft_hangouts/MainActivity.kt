package com.example.ft_hangouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ft_hangouts.repository.ContactsRepository
import com.example.ft_hangouts.ui.screen.Details
import com.example.ft_hangouts.ui.screen.Edit
import com.example.ft_hangouts.ui.home.Home
import com.example.ft_hangouts.ui.screen.Router
import com.example.ft_hangouts.ui.theme.FthangoutsTheme
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
            FthangoutsTheme {
                Surface {
                    NavHost(navController = navController, startDestination = Router.Home.route) {
                        composable(route = Router.Home.route) {
                            Home(navController = navController)
                        }
                        composable(route = Router.Details.withId()) {
                            Details(
                                navController = navController,
                                id = it.arguments?.getString(Router.Details.argId)!!
                            )
                        }
                        composable(route = Router.Edit.withId()) { Edit(viewModel, navController) }
                    }
                }
            }
        }
    }

    /*
    override fun onPause() {
        super.onPause()

        viewModel<MainViewModel>()
    }
     */
}
