package com.lcavalle.ft_hangouts.datasource.sms

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DbStrings.TableName)
data class SmsMessageDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbStrings.Fields.Id) val id: Long,
    @ColumnInfo(name = DbStrings.Fields.ContactId) val contactId: Long,
    @ColumnInfo(name = DbStrings.Fields.Timestamp) val timestamp: Long,
    @ColumnInfo(name = DbStrings.Fields.IsFromUser) val isFromUser: Boolean,
    @ColumnInfo(name = DbStrings.Fields.Content) val content: String
)


