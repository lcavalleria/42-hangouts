package com.lcavalle.ft_hangouts.ui.edit

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcavalle.ft_hangouts.Contact
import com.lcavalle.ft_hangouts.repository.ContactsRepository
import com.lcavalle.ft_hangouts.ui.ProfileImageLoader
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val contactStateName = "editingContact"
    val selectedContactState: StateFlow<Contact> =
        savedStateHandle.getStateFlow(contactStateName, Contact.empty())

    fun selectContactById(id: Long) {
        viewModelScope.launch {
            savedStateHandle[contactStateName] =
                ContactsRepository.findContactById(id) ?: Contact.empty()
        }
    }

    fun setEditContactName(name: String) {
        savedStateHandle[contactStateName] = selectedContactState.value.copy(name = name)
    }

    fun setEditContactNumber(num: String) {
        savedStateHandle[contactStateName] = selectedContactState.value.copy(number = num)
    }

    fun setEditContactMail(mail: String) {
        savedStateHandle[contactStateName] = selectedContactState.value.copy(mail = mail)
    }

    fun setEditContactPicture(uri: String) {
        savedStateHandle[contactStateName] = selectedContactState.value.copy(pictureUri = uri)
    }

    fun setEditContactIsFav(fav: Boolean) {
        savedStateHandle[contactStateName] = selectedContactState.value.copy(isFavorite = fav)
    }

    fun storeEditingContact() {
        viewModelScope.launch {
            ContactsRepository.storeContact(selectedContactState.value)
        }
    }

    fun deleteContact(context: Context) {
        viewModelScope.launch {
            ContactsRepository.deleteContact(selectedContactState.value)
            ProfileImageLoader.deleteInternal(context, selectedContactState.value.id)
        }
    }
}