package com.yudap.domain.scoring

data class TeamScore(
    val points: Int = 0,
    val games: Int = 0,
    val sets: Int = 0,
    val advantage: Boolean = false
)

data class PadelScoreState(
    val teamA: TeamScore = TeamScore(),
    val teamB: TeamScore = TeamScore(),
    val inTiebreak: Boolean = false,
    val matchFinished: Boolean = false,
    val history: List<PadelScoreState> = emptyList()
) {
    companion object {
        fun initial(): PadelScoreState {
            return PadelScoreState(
                teamA = TeamScore(),
                teamB = TeamScore(),
                inTiebreak = false,
                matchFinished = false,
                history = emptyList()
            )
        }
    }
}