package com.yudap.wear.ui.scoring

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.yudap.domain.scoring.PadelScoreState

@Composable
fun WearScoreBoard(score: PadelScoreState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${score.teamA.games} - ${score.teamB.games}",
            style = MaterialTheme.typography.display1
        )

        if (score.inTiebreak) {
            Text("TB", color = MaterialTheme.colors.secondary)
        }

        if (score.teamA.advantage) Text("AD A")
        if (score.teamB.advantage) Text("AD B")
    }
}
