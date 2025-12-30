package com.yudap.domain.scoring

data class PadelUiState(
    val score: PadelScoreState = PadelScoreState.initial(),
    val canUndo: Boolean = false
)
