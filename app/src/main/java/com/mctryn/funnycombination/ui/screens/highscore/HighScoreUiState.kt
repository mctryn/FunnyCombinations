package com.mctryn.funnycombination.ui.screens.highscore

import com.mctryn.funnycombination.data.ScoreRecord

interface HighScoreUiState {
    object NoHighScoreState : HighScoreUiState
    data class HighScoreState(
        val scores: List<ScoreRecord>
    ) : HighScoreUiState
}