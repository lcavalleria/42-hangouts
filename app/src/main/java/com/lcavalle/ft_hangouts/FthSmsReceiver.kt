package com.lcavalle.ft_hangouts

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import android.util.Log
import com.lcavalle.ft_hangouts.ReceiverUtils.goAsync
import com.lcavalle.ft_hangouts.datasource.sms.SmsMessageDto
import com.lcavalle.ft_hangouts.repository.ContactsRepository
import com.lcavalle.ft_hangouts.repository.SmsMessagesRepository

class FthSmsReceiver() : BroadcastReceiver() {
    @TargetApi(Build.VERSION_CODES.R)
    @Override
    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        Log.d(Tag, "Intent: ${intent?.action}")
        if (!intent?.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            Log.d(Tag, "Weird intent: ${intent?.action}")
            return@goAsync
        }
        val extractMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        extractMessages.forEach { smsMessage ->
            val senderNumber = smsMessage.originatingAddress ?: return@goAsync
            val timestampMs = smsMessage.timestampMillis
            // get the contact who sent us the message.
            val contact: Contact =
                ContactsRepository.findContactByNumber(senderNumber) ?: suspend {
                    val newContact =
                        Contact(
                            0L, // will be assigned by Room
                            senderNumber,
                            senderNumber,
                            senderNumber,
                            "",
                            false,
                            timestampMs
                        )
                    ContactsRepository.storeContact(newContact)
                    ContactsRepository.findContactByNumber(senderNumber)
                }.invoke() ?: return@goAsync
            val updateContact = contact.copy(lastSmsReceiveTimeMs = timestampMs)
            // update last sms received time for that contact
            ContactsRepository.storeContact(updateContact)


            val messageContent = smsMessage.displayMessageBody ?: return@goAsync

            // store received message in Room.
            SmsMessagesRepository.saveSmsMessage(
                SmsMessageDto(
                    0, // assigned by Room
                    updateContact.id,
                    timestampMs,
                    false,
                    messageContent
                )
            )
            Log.v(Tag, "Message $messageContent of contact ${contact.name} stored in Db.")
        }
    }


    companion object {
        const val Tag = "FthSmsReceiver"
    }
}