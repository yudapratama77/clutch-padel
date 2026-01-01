package com.yudap.data.db

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: PadelDatabase? = null

    fun getDatabase(context: Context): PadelDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                PadelDatabase::class.java,
                "padel_db"
            ).build().also { INSTANCE = it }
        }
    }
}
