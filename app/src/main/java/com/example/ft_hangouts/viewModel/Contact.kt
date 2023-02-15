package com.example.ft_hangouts.viewModel

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import java.util.UUID

data class Contact(
    val id: String, // contains an uuid
    val name: String,
    val number: String,
    val mail: String,
    val picture: ImageBitmap,
    val isFavorite: Boolean,
    val lastOnline: Long // epoch in ms
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "null",
        parcel.readString() ?: "null",
        parcel.readString() ?: "null",
        parcel.readString() ?: "null",
        Bitmap.CREATOR.createFromParcel(parcel).asImageBitmap(),
        parcel.readByte() != 0.toByte(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(number)
        parcel.writeString(mail)
        picture.asAndroidBitmap().writeToParcel(parcel, flags)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeLong(lastOnline)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Contact> = object : Parcelable.Creator<Contact> {
            override fun createFromParcel(parcel: Parcel): Contact {
                return Contact(parcel)
            }

            override fun newArray(size: Int): Array<Contact?> {
                return arrayOfNulls(size)
            }
        }

        val empty = Contact(
            id = UUID.randomUUID().toString(),
            name = "",
            number = "",
            mail = "",
            picture = ImageBitmap(19, 30),
            isFavorite = false,
            lastOnline = 0
        )
    }
}