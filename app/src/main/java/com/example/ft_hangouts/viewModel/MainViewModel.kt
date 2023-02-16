package com.example.ft_hangouts.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import java.util.*

class MainViewModel(
    /**
     * if the system kills the app for resources, the savedState will not be deleted.
     */
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val selectedContactState: StateFlow<Contact?> =
        savedStateHandle.getStateFlow("editingContact", null)

    /**
     * will add the contact if it doesn't exist
     */
    fun saveContact(contact: Contact) {
        val currentContacts: Map<UUID, Contact> = savedStateHandle["contacts"] ?: emptyMap()
        savedStateHandle["contacts"] = currentContacts + Pair(contact.id, contact)
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