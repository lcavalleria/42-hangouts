package com.lcavalle.ft_hangouts.ui.details

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcavalle.ft_hangouts.Contact
import com.lcavalle.ft_hangouts.repository.ContactsRepository
import com.lcavalle.ft_hangouts.ui.ProfileImageLoader
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val contactStateName = "selectedContact"

    val selectedContactState: StateFlow<Contact> =
        savedStateHandle.getStateFlow(contactStateName, Contact.empty())

    fun selectContactById(id: Long) {
        viewModelScope.launch {
            savedStateHandle[contactStateName] =
                ContactsRepository.findContactById(id) ?: Contact.empty()
        }
    }
}
