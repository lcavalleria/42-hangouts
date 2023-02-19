package com.lcavalle.ft_hangouts.repository

import com.lcavalle.ft_hangouts.datasource.FtHangoutsDb
import com.lcavalle.ft_hangouts.datasource.sms.SmsMessageDao
import com.lcavalle.ft_hangouts.datasource.sms.SmsMessageDto

object SmsMessagesRepository {
    private val dao: SmsMessageDao = FtHangoutsDb.getInstance().smsMessageDao()

    val allSmsMessages = dao.getAllSmsMessages()

    suspend fun saveSmsMessage(smsMessageDao: SmsMessageDto) = dao.addSmsMessage(smsMessageDao)
}