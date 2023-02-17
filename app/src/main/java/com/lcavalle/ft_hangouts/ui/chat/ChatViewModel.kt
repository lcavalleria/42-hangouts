package com.lcavalle.ft_hangouts.ui.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lcavalle.ft_hangouts.repository.ContactsRepository
import com.lcavalle.ft_hangouts.viewModel.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val currentMessageContent: StateFlow<String> =
        savedStateHandle.getStateFlow("currentMessageContent", "")

    fun getContactById(id: String): StateFlow<Contact> {
        return MutableStateFlow(ContactsRepository.contactsPlaceholder.first { it.id == id })
    }

    fun getCurrentMessage(): StateFlow<String> {
        return currentMessageContent
    }

    fun getMessageHistoryById(id: String): StateFlow<Map<Long, Pair<Boolean, String>>> {
        // <Timestamp, Pair<isMine,Message>>
        return MutableStateFlow(ContactsRepository.messagesPlaceholderById(id))
    }

    fun setCurrentMessage(message: String) {
        savedStateHandle["currentMessageContent"] = message
    }


}