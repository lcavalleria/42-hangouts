package com.lcavalle.ft_hangouts.ui.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcavalle.ft_hangouts.Contact
import com.lcavalle.ft_hangouts.datasource.sms.SmsMessageDto
import com.lcavalle.ft_hangouts.repository.ContactsRepository
import com.lcavalle.ft_hangouts.repository.SmsMessagesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val currentMessageStateName = "currentMessageContent"
    private val contactStateName = "selectedContact"

    private val currentMessageContent: StateFlow<String> =
        savedStateHandle.getStateFlow(currentMessageStateName, "")

    val selectedContact: StateFlow<Contact> =
        savedStateHandle.getStateFlow(contactStateName, Contact.empty())

    fun selectContactById(id: Long) {
        viewModelScope.launch {
            savedStateHandle[contactStateName] = ContactsRepository.findContactById(id)
        }
    }

    fun getCurrentMessage(): StateFlow<String> {
        return currentMessageContent
    }

    /**
     * Returns all sms of that contact (both sent and received).
     * Sorted by timestamp DESCENDING, to be used with reverseLayout=true in LazyColumn
     */
    fun getMessageHistoryById(contactId: Long): StateFlow<List<SmsMessageDto>> {
        return SmsMessagesRepository.allSmsMessages
            .map { messages ->
                messages.filter { it.contactId == contactId }
                    .sortedByDescending { it.timestamp }
            }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    }

    fun setCurrentMessage(message: String) {
        savedStateHandle[currentMessageStateName] = message
    }

    fun storeMessage(message: SmsMessageDto) {
        viewModelScope.launch {
            SmsMessagesRepository.saveSmsMessage(message)
        }
    }

}