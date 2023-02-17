package com.lcavalle.ft_hangouts.ui.details

import androidx.lifecycle.ViewModel
import com.lcavalle.ft_hangouts.repository.ContactsRepository
import com.lcavalle.ft_hangouts.viewModel.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsViewModel : ViewModel() {
    fun getContactById(id: String): StateFlow<Contact> {
        return MutableStateFlow(ContactsRepository.contactsPlaceholder.first { it.id == id })
    }
}
