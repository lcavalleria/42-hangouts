package com.example.ft_hangouts.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.viewModel.Contact
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val contactsState: StateFlow<List<Contact>> =
        savedStateHandle.getStateFlow("contacts", emptyList())

    val timeSetToBackgroundState: StateFlow<Long> =
        savedStateHandle.getStateFlow("backgroundTimeMs", 0)

    /**
     * to load all the contacts at once
     */
    fun loadContacts(contacts: List<Contact>) {
        savedStateHandle["contacts"] = contacts
    }

    /**
     *
     */
    fun saveBackgroundTime() {
        savedStateHandle["backgroundTimeMs"] = System.currentTimeMillis()
    }
}