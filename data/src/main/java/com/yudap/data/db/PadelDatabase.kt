package com.yudap.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yudap.data.db.dao.MatchDao
import com.yudap.data.db.entity.MatchEntity

@Database(
    entities = [MatchEntity::class],
    version = 1
)
abstract class PadelDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}