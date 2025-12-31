package com.yudap.data.repository

import com.yudap.data.db.dao.MatchDao
import com.yudap.data.mapper.toEntity
import com.yudap.data.mapper.toScoreState
import com.yudap.domain.scoring.PadelScoreState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MatchRepository (
    private val dao: MatchDao
) {

    fun observeHistory(): Flow<List<PadelScoreState>> = dao.getAllMatches().map { list ->
        list.map { it.toScoreState() }
    }

    suspend fun saveCurrentMatch(state: PadelScoreState) {
        dao.saveMatch(state.toEntity())
    }

    suspend fun resumeMatch(): PadelScoreState? {
        return dao.getOngoingMatch()?.toScoreState()
    }
}