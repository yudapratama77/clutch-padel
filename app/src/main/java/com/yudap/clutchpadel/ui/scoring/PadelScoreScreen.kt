package com.yudap.clutchpadel.ui.scoring

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yudap.domain.scoring.ScoreEvent

@Composable
fun PadelScoreScreen(
    viewModel: PadelViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    PadelScoreContent(
        state = state,
        onTeamAPoint = { viewModel.onEvent(ScoreEvent.TeamAPoint) },
        onTeamBPoint = { viewModel.onEvent(ScoreEvent.TeamBPoint) },
        onUndo = { viewModel.onEvent(ScoreEvent.Undo) }
    )
}