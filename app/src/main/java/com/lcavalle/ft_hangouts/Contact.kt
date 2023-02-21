package com.lcavalle.ft_hangouts

import android.os.Parcel
import android.os.Parcelable
import com.lcavalle.ft_hangouts.datasource.contact.ContactDto

data class Contact(
    val id: Long,
    val name: String,
    val number: String,
    val mail: String,
    val pictureUri: String,
    val isFavorite: Boolean,
    val lastSmsReceiveTimeMs: Long // epoch in ms
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "null",
        parcel.readString() ?: "null",
        parcel.readString() ?: "null",
        parcel.readString() ?: "null",
        parcel.readByte() != 0.toByte(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(number)
        parcel.writeString(mail)
        parcel.writeString(pictureUri)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeLong(lastSmsReceiveTimeMs)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object {
        fun fromDto(dto: ContactDto): Contact {
            return Contact(
                dto.id,
                dto.name,
                dto.number,
                dto.mail,
                dto.picture,
                dto.isFavorite,
                dto.lastSmsReceiveTimeMs
            )
        }

        fun empty() = Contact(
            id = 0, // auto assigned by room, so this is irrelevant.
            name = "",
            number = "",
            mail = "",
            pictureUri = "",
            isFavorite = false,
            lastSmsReceiveTimeMs = System.currentTimeMillis()
        )

        @JvmField
        val CREATOR: Parcelable.Creator<Contact> = object : Parcelable.Creator<Contact> {
            override fun createFromParcel(parcel: Parcel): Contact {
                return Contact(parcel)
            }

            override fun newArray(size: Int): Array<Contact?> {
                return arrayOfNulls(size)
            }
        }
    }
}