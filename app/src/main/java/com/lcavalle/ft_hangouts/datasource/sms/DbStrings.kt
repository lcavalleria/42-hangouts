package com.lcavalle.ft_hangouts.datasource.sms

object DbStrings {
    const val TableName = "sms_message"

    object Fields {
        const val Id = "id"
        const val ContactId = "contact_id"
        const val Timestamp = "timestamp"
        const val IsFromUser = "isFromUser"
        const val Content = "content"
    }
}