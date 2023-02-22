package com.lcavalle.ft_hangouts

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lcavalle.ft_hangouts.datasource.DateUtils
import com.lcavalle.ft_hangouts.ui.chat.Chat
import com.lcavalle.ft_hangouts.ui.details.Details
import com.lcavalle.ft_hangouts.ui.edit.Edit
import com.lcavalle.ft_hangouts.ui.home.Home
import com.lcavalle.ft_hangouts.ui.theme.FthangoutsTheme

class MainActivity() : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPermissions()

        setContent {
            val navController = rememberNavController()
            FthangoutsTheme {
                Surface {
                    NavHost(navController = navController, startDestination = Router.Home.route) {
                        composable(route = Router.Home.route) {
                            viewModel.setIsHome(true)
                            Home(navController = navController)
                        }
                        composable(
                            route = Router.Details.withId(),
                            arguments = listOf(navArgument(Router.Details.argId) {
                                type = NavType.LongType
                            })
                        ) {
                            viewModel.setIsHome(false)
                            Details(
                                navController = navController,
                                id = it.arguments?.getLong(Router.Details.argId)!!
                            )
                        }
                        composable(
                            route = Router.Edit.withId(),
                            arguments = listOf(navArgument(Router.Edit.argId) {
                                type = NavType.LongType
                            })
                        ) {
                            viewModel.setIsHome(false)
                            Edit(
                                navController = navController,
                                id = it.arguments?.getLong(Router.Edit.argId) ?: 0
                            )
                        }
                        composable(
                            route = Router.Edit.route
                        ) {
                            viewModel.setIsHome(false)
                            Edit(
                                navController = navController
                            )
                        }
                        composable(
                            route = Router.Chat.withId(),
                            arguments = listOf(navArgument(Router.Chat.argId) {
                                type = NavType.LongType
                            })
                        ) {
                            viewModel.setIsHome(false)
                            Chat(
                                navController = navController,
                                id = it.arguments?.getLong(Router.Edit.argId)!!
                            )
                        }
                    }
                }
            }
        }
    }


    override fun onPause() {
        super.onPause()

        viewModel.setPauseTime()
    }

    override fun onResume() {
        super.onResume()

        val pausedTime = viewModel.pauseTimeState.value
        if (pausedTime > 0 && viewModel.isHomeScreenState.value) Toast.makeText(
            this.applicationContext, "The app was paused at ${
                DateUtils.formatDate(
                    pausedTime, resources.configuration
                )
            }", Toast.LENGTH_LONG
        ).show()
    }

    private fun getPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.BROADCAST_SMS
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    android.Manifest.permission.SEND_SMS,
                    android.Manifest.permission.RECEIVE_SMS,
                    android.Manifest.permission.BROADCAST_SMS,
                    android.Manifest.permission.READ_SMS,
                    android.Manifest.permission.CALL_PHONE,
                ), 0
            )
        }
    }
}
