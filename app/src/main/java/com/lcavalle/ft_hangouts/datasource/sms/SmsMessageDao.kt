package com.lcavalle.ft_hangouts.datasource.sms

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SmsMessageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSmsMessage(smsMessage: SmsMessageDto)

    @Query("SELECT * FROM ${DbStrings.TableName}")
    fun getAllSmsMessages(): Flow<List<SmsMessageDto>>
}