package com.yudap.clutchpadel.ui.scoring

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.yudap.domain.scoring.PadelScoreState

@Composable
fun ScoreBoard(score: PadelScoreState) {
    Column {
        Text("Sets: ${score.teamA.sets} - ${score.teamB.sets}")
        Text("Games: ${score.teamA.games} - ${score.teamB.games}")
        Text("Points: ${score.teamA.points} - ${score.teamB.points}")

        if (score.inTiebreak) {
            Text("TIEBREAK")
        }

        if (score.teamA.advantage) Text("Advantage A")
        if (score.teamB.advantage) Text("Advantage B")
    }
}
