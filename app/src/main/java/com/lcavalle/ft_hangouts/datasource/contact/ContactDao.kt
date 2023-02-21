package com.lcavalle.ft_hangouts.datasource.contact

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contact: ContactDto)

    @Query("SELECT * FROM ${DbStrings.TableName} WHERE ${DbStrings.Fields.Id} = :id")
    suspend fun findContactById(id: Long): ContactDto?

    @Query("SELECT * FROM ${DbStrings.TableName} WHERE ${DbStrings.Fields.Number} = :number")
    suspend fun findContactByNumber(number: String): ContactDto?

    @Query("SELECT * FROM ${DbStrings.TableName}")
    fun getAllContacts(): Flow<List<ContactDto>>

    @Update
    suspend fun updateContact(contact: ContactDto)

    @Delete
    suspend fun deleteContact(contact: ContactDto)

}