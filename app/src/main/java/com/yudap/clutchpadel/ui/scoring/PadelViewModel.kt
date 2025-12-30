package com.yudap.clutchpadel.ui.scoring

import androidx.lifecycle.ViewModel
import com.yudap.domain.scoring.PadelScoreState
import com.yudap.domain.scoring.PadelScoringEngine
import com.yudap.domain.scoring.PadelUiState
import com.yudap.domain.scoring.ScoreEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PadelViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        PadelUiState(
            score = PadelScoreState.initial(),
            canUndo = false
        )
    )

    val uiState: StateFlow<PadelUiState> = _uiState

    fun onEvent(event: ScoreEvent) {
        val current = _uiState.value.score
        val next = PadelScoringEngine.applyEvent(current, event)

        _uiState.value = PadelUiState(
            score = next,
            canUndo = next.history.isNotEmpty()
        )
    }

    fun reset() {
        _uiState.value = PadelUiState(
            score = PadelScoreState.initial(),
            canUndo = false
        )
    }
}
