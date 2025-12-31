package com.yudap.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val teamAGames: Int,
    val teamBGames: Int,
    val teamASets: Int,
    val teamBSets: Int,

    val inTiebreak: Boolean,
    val isFinished: Boolean,

    val timestamp: Long = System.currentTimeMillis()
)
