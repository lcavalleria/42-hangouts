package com.lcavalle.ft_hangouts.ui

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.net.toUri
import com.lcavalle.ft_hangouts.MainActivity
import java.io.FileNotFoundException
import java.io.IOException

object ProfileImageLoader {
    private fun profileImageFileName(contactId: Long): String {
        return "profile_image_$contactId.png"
    }

    fun loadExternal(contentResolver: ContentResolver, uri: Uri?): Bitmap? {
        if (uri == null) return null
        return try {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(contentResolver, uri)
            )
        } catch (e: IOException) {
            null
        }
    }

    fun storeInternal(context: Context, contactId: Long, bitmap: Bitmap): Uri? {
        val fileName = profileImageFileName(contactId)

        val fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)


        bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream)
        val uri = context.getFileStreamPath(fileName)?.toUri()
        fileOutputStream.close()

        Log.i(MainActivity.TAG, "Stored image $fileName")
        return uri
    }

    fun loadInternal(context: Context, contactId: Long): ImageBitmap? {
        val imageName = profileImageFileName(contactId)

        return try {
            val fileInputStream = context.openFileInput(imageName)
            val bitmap = BitmapFactory.decodeStream(fileInputStream)
            fileInputStream.close()
            Log.i(MainActivity.TAG, "File $imageName loaded")
            bitmap.asImageBitmap()
        } catch (e: FileNotFoundException) {
            Log.i(MainActivity.TAG, "File $imageName failed to load")
            null
        }
    }

    fun deleteInternal(context: Context, contactId: Long) {
        context.deleteFile(profileImageFileName(contactId))
    }
}