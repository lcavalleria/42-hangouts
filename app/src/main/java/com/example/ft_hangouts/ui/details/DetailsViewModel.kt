package com.example.ft_hangouts.ui.details

import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.repository.ContactsRepository
import com.example.ft_hangouts.viewModel.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsViewModel : ViewModel() {


    fun getContactById(id: String): StateFlow<Contact> {
        return MutableStateFlow(ContactsRepository.placeholder.first { it.id == id })
    }
}
