package com.lcavalle.ft_hangouts.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcavalle.ft_hangouts.Contact
import com.lcavalle.ft_hangouts.repository.ContactsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel() : ViewModel() {

    val contactsState: StateFlow<List<Contact>> =
        ContactsRepository.allContacts.map { contactsList ->
            contactsList.map { Contact.fromDto(it) }.sortedBy { !it.isFavorite }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}