package com.lcavalle.ft_hangouts.ui.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lcavalle.ft_hangouts.repository.ContactsRepository
import com.lcavalle.ft_hangouts.viewModel.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val selectedContactState: StateFlow<Contact?> =
        savedStateHandle.getStateFlow("editingContact", null)

    fun getContactById(id: String): StateFlow<Contact> {
        return MutableStateFlow(ContactsRepository.contactsPlaceholder.first { it.id == id })
    }

    /**
     * keeps the contact that will be / is being edited in the Edit screen.
     */
    fun setEditContact(contact: Contact) {
        savedStateHandle["editingContact"] = contact
    }

    fun setEditContactName(name: String) {
        savedStateHandle["editingContact"] = selectedContactState.value?.copy(name = name)
    }

    fun setEditContactNumber(num: String) {
        savedStateHandle["editingContact"] = selectedContactState.value?.copy(number = num)
    }

    fun setEditContactMail(mail: String) {
        savedStateHandle["editingContact"] = selectedContactState.value?.copy(mail = mail)
    }

    fun setEditContactIsFav(fav: Boolean) {
        savedStateHandle["editingContact"] = selectedContactState.value?.copy(isFavorite = fav)
    }
}