package com.mctryn.funnycombination.ui.screens.highscore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mctryn.funnycombination.data.ScoreBoardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HighScoreViewModel(
    private val scoreBoardRepository: ScoreBoardRepository
) : ViewModel() {

    private val mutableStateFlow =
        MutableStateFlow<HighScoreUiState>(HighScoreUiState.NoHighScoreState)
    val state = mutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val scores = scoreBoardRepository.getAllValues().sortedByDescending { it.score }
            if (scores.isEmpty()) {
                mutableStateFlow.value = HighScoreUiState.NoHighScoreState
            } else {
                mutableStateFlow.value = HighScoreUiState.HighScoreState(scores)
            }
        }
    }
}