package com.lcavalle.ft_hangouts.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lcavalle.ft_hangouts.datasource.contact.ContactDao
import com.lcavalle.ft_hangouts.datasource.contact.ContactDto
import com.lcavalle.ft_hangouts.datasource.sms.SmsMessageDao
import com.lcavalle.ft_hangouts.datasource.sms.SmsMessageDto

@Database(
    entities = [(ContactDto::class), (SmsMessageDto::class)],
    version = 1,
    // i don't trust 42 computer to have enough memory, even if it should be very light.
    exportSchema = false
)
abstract class FtHangoutsDb : RoomDatabase() {
    abstract fun contactDao(): ContactDao
    abstract fun smsMessageDao(): SmsMessageDao

    companion object {
        private const val DatabaseName = "ft_hangouts_database"
        // for reference: https://medium.com/jetpack-composers/jetpack-compose-room-db-b7b23bd6b189

        /*The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
        It means that changes made by one thread to INSTANCE are visible to all other threads immediately.*/

        @Volatile
        private var INSTANCE: FtHangoutsDb? = null

        fun init(context: Context): FtHangoutsDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FtHangoutsDb::class.java,
                        DatabaseName
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

        fun getInstance(): FtHangoutsDb = INSTANCE!!
    }
}
