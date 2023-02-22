package com.lcavalle.ft_hangouts.repository

import com.lcavalle.ft_hangouts.Contact
import com.lcavalle.ft_hangouts.datasource.FtHangoutsDb
import com.lcavalle.ft_hangouts.datasource.contact.ContactDao
import com.lcavalle.ft_hangouts.datasource.contact.ContactDto

object ContactsRepository {
    private val dao: ContactDao = FtHangoutsDb.getInstance().contactDao()

    val allContacts = dao.getAllContacts()

    suspend fun findContactById(id: Long): Contact? {
        val dto = dao.findContactById(id)
        return if (dto != null) Contact.fromDto(dto) else null
    }

    suspend fun findContactByNumber(number: String): Contact? {
        val dto = dao.findContactByNumber(number)
        return if (dto != null) Contact.fromDto(dto) else null
    }

    suspend fun storeContact(contact: Contact) {
        val dto = ContactDto.fromContact(contact)
        if (dto.id == 0L) dao.addContact(dto)
        else dao.updateContact(dto)
    }

    suspend fun deleteContact(contact: Contact) {
        val dto = ContactDto.fromContact(contact)
        dao.deleteContact(dto)
    }

}