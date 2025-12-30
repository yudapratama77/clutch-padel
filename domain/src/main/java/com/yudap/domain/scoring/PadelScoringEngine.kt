package com.yudap.domain.scoring

object PadelScoringEngine {
    fun applyEvent(
        state: PadelScoreState,
        event: ScoreEvent
    ): PadelScoreState {
        return when (event) {
            ScoreEvent.Undo -> {
                val previous = state.history.lastOrNull() ?: return state
                previous.copy(
                    history = state.history.dropLast(1)
                )
            }
            ScoreEvent.TeamAPoint -> scorePoint(state, true)
            ScoreEvent.TeamBPoint -> scorePoint(state, false)
        }
    }

    private fun scorePoint(
        state: PadelScoreState,
        teamA: Boolean
    ): PadelScoreState {
        if (state.matchFinished) return state

        val next = if (state.inTiebreak)
            scoreTiebreak(state, teamA)
        else
            scoreRegular(state, teamA)

        return next.copy(
            history = state.history + state
        )
    }

    private fun scoreRegular(
        state: PadelScoreState,
        teamA: Boolean
    ): PadelScoreState {
        val a = state.teamA
        val b = state.teamB

        val scoring = if (teamA) a else b
        val opponent = if (teamA) b else a

        // Deuce logic
        if (scoring.points >= 40 && opponent.points >= 40) {
            return when {
                opponent.advantage -> resetDeuce(state)
                scoring.advantage -> winGame(state, teamA)
                else -> giveAdvantage(state, teamA)
            }
        }

        // Normal points
        val newPoints = when (scoring.points) {
            0 -> 15
            15 -> 30
            30 -> 40
            else -> return winGame(state, teamA)
        }

        return updatePoints(state, teamA, newPoints)
    }

    private fun scoreTiebreak(
        state: PadelScoreState,
        teamA: Boolean
    ): PadelScoreState {
        val a = state.teamA
        val b = state.teamB

        val scoring = if (teamA) a else b
        val opponent = if (teamA) b else a

        val newPoints = scoring.points + 1

        if (newPoints >= 7 && newPoints - opponent.points >= 2) {
            return winSet(state, teamA)
        }

        return updateTiebreakPoints(state, teamA, newPoints)
    }

    private fun winGame(state: PadelScoreState, teamA: Boolean): PadelScoreState {
        val newGamesA = if (teamA) state.teamA.games + 1 else state.teamA.games
        val newGamesB = if (!teamA) state.teamB.games + 1 else state.teamB.games

        val enterTiebreak = newGamesA == 6 && newGamesB == 6

        val nextState = state.copy(
            teamA = state.teamA.copy(points = 0, advantage = false, games = newGamesA),
            teamB = state.teamB.copy(points = 0, advantage = false, games = newGamesB),
            inTiebreak = enterTiebreak
        )

        return if (isSetWon(nextState)) winSet(nextState, teamA) else nextState
    }

    private fun winSet(state: PadelScoreState, teamA: Boolean): PadelScoreState {
        val newSetsA = if (teamA) state.teamA.sets + 1 else state.teamA.sets
        val newSetsB = if (!teamA) state.teamB.sets + 1 else state.teamB.sets

        val finished = newSetsA == 2 || newSetsB == 2

        return state.copy(
            teamA = state.teamA.copy(points = 0, games = 0, sets = newSetsA),
            teamB = state.teamB.copy(points = 0, games = 0, sets = newSetsB),
            inTiebreak = false,
            matchFinished = finished
        )
    }

    private fun resetDeuce(state: PadelScoreState): PadelScoreState {
        return state.copy(
            teamA = state.teamA.copy(advantage = false),
            teamB = state.teamB.copy(advantage = false)
        )
    }

    private fun giveAdvantage(
        state: PadelScoreState,
        teamA: Boolean
    ): PadelScoreState {
        return state.copy(
            teamA = state.teamA.copy(advantage = teamA),
            teamB = state.teamB.copy(advantage = !teamA)
        )
    }

    private fun updatePoints(
        state: PadelScoreState,
        teamA: Boolean,
        points: Int
    ): PadelScoreState {
        return if (teamA) {
            state.copy(teamA = state.teamA.copy(points = points))
        } else {
            state.copy(teamB = state.teamB.copy(points = points))
        }
    }

    private fun updateTiebreakPoints(
        state: PadelScoreState,
        teamA: Boolean,
        points: Int
    ): PadelScoreState {
        return if (teamA) {
            state.copy(teamA = state.teamA.copy(points = points))
        } else {
            state.copy(teamB = state.teamB.copy(points = points))
        }
    }

    private fun isSetWon(state: PadelScoreState): Boolean {
        val a = state.teamA.games
        val b = state.teamB.games

        return (a >= 6 || b >= 6) && kotlin.math.abs(a - b) >= 2
    }
}