package com.mctryn.funnycombination.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mctryn.funnycombination.domain.CheckResult
import com.mctryn.funnycombination.domain.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameRepository: GameRepository
) : ViewModel() {

    private val mutableStateFlow: MutableStateFlow<GameUIState> =
        MutableStateFlow(GameUIState.ShowSequenceState(gameRepository.getInitialSequence()))
    val state = mutableStateFlow.asStateFlow()

    fun animationFinished() {
        val initialAfterAnimation =
            gameRepository.getInitState()
        mutableStateFlow.value = GameUIState.RepeatSequenceState(
            baseList = initialAfterAnimation.baseItems,
            chosenList = emptyList(),
            score = initialAfterAnimation.bestScore
        )
    }

    fun itemClicked(resId: Int) {
        viewModelScope.launch {
            val repositoryResult = gameRepository.itemClicked(resId)
            when (repositoryResult) {
                is CheckResult.ShouldShowNextCharacter -> {
                    mutableStateFlow.value = GameUIState.RepeatSequenceState(
                        baseList = repositoryResult.baseItems.toList(),
                        chosenList = repositoryResult.chosenItems.toList(),
                        score = repositoryResult.bestScore
                    )
                }

                is CheckResult.ShouldShowNextSequence -> {
                    mutableStateFlow.value = GameUIState.ShowSequenceState(
                        sequenceList = repositoryResult.nextSequence
                    )
                }

                is CheckResult.ShouldShowFail -> {
                    mutableStateFlow.value = GameUIState.Result(
                        savedToScoreBoard = repositoryResult.savedToScoreBoard,
                        score = repositoryResult.score
                    )
                }
            }
        }
    }


    fun tryAgain() {
        val result = gameRepository.tryAgain()
        mutableStateFlow.value = GameUIState.ShowSequenceState(result.nextSequence)
    }
}