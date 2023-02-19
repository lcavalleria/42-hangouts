package com.lcavalle.ft_hangouts.datasource.contact

object DbStrings {
    const val TableName = "contacts"

    object Fields {
       const val Id = "id"
        const val Name = "name"
        const val Number = "number"
        const val Mail = "mail"
        const val Picture = "picture"
        const val IsFavorite = "is_favorite"
        const val LastSmsReceiveTimeMs = "last_sms_receive_time_ms"
    }
}