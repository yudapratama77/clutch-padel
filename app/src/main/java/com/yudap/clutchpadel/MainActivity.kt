package com.yudap.clutchpadel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yudap.clutchpadel.ui.scoring.PadelScoreContent
import com.yudap.clutchpadel.ui.scoring.PadelScoreScreen
import com.yudap.clutchpadel.ui.theme.ClutchPadelTheme
import com.yudap.domain.scoring.PadelScoreState
import com.yudap.domain.scoring.PadelUiState
import com.yudap.domain.scoring.TeamScore

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClutchPadelTheme {
                PadelScoreScreen()
            }
        }
    }
}

private val PreviewUiState = PadelUiState(
    score = PadelScoreState(
        teamA = TeamScore(points = 40, games = 5, sets = 1),
        teamB = TeamScore(points = 40, games = 5, sets = 1),
        inTiebreak = false
    ),
    canUndo = true
)

@Preview(showBackground = true)
@Composable
fun PadelScorePreview() {
    PadelScoreContent(
        state = PreviewUiState,
        onTeamAPoint = {},
        onTeamBPoint = {},
        onUndo = {}
    )
}