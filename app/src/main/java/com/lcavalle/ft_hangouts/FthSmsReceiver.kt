package com.lcavalle.ft_hangouts

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.telephony.SmsManager

class FthSmsService() : Service() {
    companion object {
        const val SmsRequestCode = 42
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        val token = startSmsListener()
    }

    private fun startSmsListener(): String {
        val smsManager: SmsManager = getSystemService(SmsManager::class.java)
        return smsManager.createAppSpecificSmsToken(
            PendingIntent.getActivity(
                this,
                SmsRequestCode,
                Intent(this, this::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}