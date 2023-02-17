package com.lcavalle.ft_hangouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lcavalle.ft_hangouts.ui.chat.Chat
import com.lcavalle.ft_hangouts.ui.details.Details
import com.lcavalle.ft_hangouts.ui.edit.Edit
import com.lcavalle.ft_hangouts.ui.home.Home
import com.lcavalle.ft_hangouts.ui.theme.FthangoutsTheme

class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
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
                        composable(route = Router.Edit.withId()) {
                            Edit(
                                navController = navController,
                                id = it.arguments?.getString(Router.Edit.argId)!!
                            )
                        }
                        composable(route = Router.Chat.withId()) {
                            Chat(
                                id = it.arguments?.getString(Router.Edit.argId)!!
                            )
                        }
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
