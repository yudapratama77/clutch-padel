package com.yudap.wear.ui.scoring

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import com.yudap.domain.scoring.ScoreEvent

@Composable
fun PadelWearScreen(
    viewModel: PadelWearViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        timeText = { TimeText() }
    ) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                WearScoreBoard(state.score)
            }

            item {
                WearActionButton(
                    label = "Team A +",
                    onClick = { viewModel.onEvent(ScoreEvent.TeamAPoint) }
                )
            }

            item {
                WearActionButton(
                    label = "Team B +",
                    onClick = { viewModel.onEvent(ScoreEvent.TeamBPoint) }
                )
            }

            if (state.canUndo) {
                item {
                    WearActionButton(
                        label = "Undo",
                        onClick = { viewModel.onEvent(ScoreEvent.Undo) }
                    )
                }
            }
        }
    }
}
