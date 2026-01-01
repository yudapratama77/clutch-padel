package com.yudap.clutchpadel.ui.scoring

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yudap.domain.scoring.PadelUiState

@Composable
fun PadelScoreContent(
    state: PadelUiState,
    onTeamAPoint: () -> Unit,
    onTeamBPoint: () -> Unit,
    onUndo: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ScoreBoard(state.score)

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = onTeamAPoint) {
                Text("Team A +")
            }
            Button(onClick = onTeamBPoint) {
                Text("Team B +")
            }
        }

        Button(
            enabled = state.canUndo,
            onClick = onUndo
        ) {
            Text("Undo")
        }
    }
}
