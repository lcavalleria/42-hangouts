package com.lcavalle.ft_hangouts.datasource.contact

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DbStrings.TableName)
data class ContactDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbStrings.Fields.Id) val id: Long,
    @ColumnInfo(name = DbStrings.Fields.Name) val name: String,
    @ColumnInfo(name = DbStrings.Fields.Number) val number: String,
    @ColumnInfo(name = DbStrings.Fields.Mail) val mail: String,
    @ColumnInfo(name = DbStrings.Fields.Picture) val picture: String,
    @ColumnInfo(name = DbStrings.Fields.IsFavorite) val isFavorite: Boolean,
    @ColumnInfo(name = DbStrings.Fields.LastSmsReceiveTimeMs) val lastSmsReceiveTimeMs: Long
)

