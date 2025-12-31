package com.yudap.clutchpadel.ui.scoring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudap.data.repository.MatchRepository
import com.yudap.domain.scoring.PadelScoreState
import com.yudap.domain.scoring.PadelScoringEngine
import com.yudap.domain.scoring.PadelUiState
import com.yudap.domain.scoring.ScoreEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PadelViewModel(
    private val repository: MatchRepository
) : ViewModel() {

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

        persist()
    }

    fun reset() {
        _uiState.value = PadelUiState(
            score = PadelScoreState.initial(),
            canUndo = false
        )
    }

    private fun persist() {
        viewModelScope.launch {
            repository.saveCurrentMatch(_uiState.value.score)
        }
    }
}
