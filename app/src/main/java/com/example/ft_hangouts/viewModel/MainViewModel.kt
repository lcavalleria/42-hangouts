package com.example.ft_hangouts.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant
import java.util.*

class MainViewModel(
    /**
     * if the system kills the app for resources, the savedState will not be deleted.
     */
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val contactsState: StateFlow<Map<String, Contact>> =
        savedStateHandle.getStateFlow("contacts", emptyMap())
    val timeSetToBackgroundState: StateFlow<Instant> =
        savedStateHandle.getStateFlow("backgroundTimeMs", Instant.now())
    val editingContactState: StateFlow<Contact?> =
        savedStateHandle.getStateFlow("editingContact", null)

    /**
     * will add the contact if it doesn't exist
     */
    fun saveContact(contact: Contact) {
        val currentContacts: Map<UUID, Contact> = savedStateHandle["contacts"] ?: emptyMap()
        savedStateHandle["contacts"] = currentContacts + Pair(contact.id, contact)
    }

    /**
     * to load all the contacts at once
     */
    fun loadContacts(contacts: List<Contact>) {
        savedStateHandle["contacts"] = contacts.associateBy { c -> c.id }
    }

    /**
     * keeps the contact that will be / is being edited in the Edit screen.
     */
    fun setEditContact(contact: Contact) {
        savedStateHandle["editingContact"] = contact
    }

    fun saveBackgroundTime() {
        savedStateHandle["backgroundTimeMs"] = Instant.now()
    }
}