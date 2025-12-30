package com.yudap.domain.scoring

sealed class ScoreEvent {
    object TeamAPoint : ScoreEvent()
    object TeamBPoint : ScoreEvent()
    object Undo : ScoreEvent()
}