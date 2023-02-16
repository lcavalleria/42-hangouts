package com.example.ft_hangouts.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.ft_hangouts.viewModel.Contact
import com.example.ft_hangouts.viewModel.MainViewModel

@Composable
fun Chat(viewModel: MainViewModel) {
    val selectedContactState = viewModel.selectedContactState.collectAsState()
    val selectedContactValue: Contact? = selectedContactState.value


}