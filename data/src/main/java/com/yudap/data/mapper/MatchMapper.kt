package com.yudap.data.mapper

import com.yudap.data.db.entity.MatchEntity
import com.yudap.domain.scoring.PadelScoreState
import com.yudap.domain.scoring.TeamScore

fun PadelScoreState.toEntity(): MatchEntity {
    return MatchEntity(
        teamAGames = teamA.games,
        teamBGames = teamB.games,
        teamASets = teamA.sets,
        teamBSets = teamB.sets,
        inTiebreak = inTiebreak,
        isFinished = matchFinished
    )
}

fun MatchEntity.toScoreState(): PadelScoreState {
    return PadelScoreState(
        teamA = TeamScore(
            games = teamAGames,
            sets = teamASets
        ),
        teamB = TeamScore(
            games = teamBGames,
            sets = teamBSets
        ),
        inTiebreak = inTiebreak,
        matchFinished = isFinished
    )
}