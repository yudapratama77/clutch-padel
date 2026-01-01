package com.yudap.domain.share

import com.yudap.domain.scoring.PadelScoreState

object MatchShareFormatter  {
    fun format(score: PadelScoreState): String {
        return """
        🏓 Padel Match Result

        Sets:
        Team A: ${score.teamA.sets}
        Team B: ${score.teamB.sets}

        Games:
        ${score.teamA.games} - ${score.teamB.games}

        ${if (score.matchFinished) "Match Finished ✅" else "In Progress ⏳"}
        """.trimIndent()
    }
}