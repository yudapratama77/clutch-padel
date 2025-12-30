package com.yudap.clutchpadel.ui.scoring

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yudap.domain.scoring.ScoreEvent

@Composable
fun PadelScoreScreen(
    viewModel: PadelViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ScoreBoard(state.score)

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { viewModel.onEvent(ScoreEvent.TeamAPoint) }) {
                Text("Team A +")
            }
            Button(onClick = { viewModel.onEvent(ScoreEvent.TeamBPoint) }) {
                Text("Team B +")
            }
        }

        Button(
            enabled = state.canUndo,
            onClick = { viewModel.onEvent(ScoreEvent.Undo) }
        ) {
            Text("Undo")
        }
    }
}
