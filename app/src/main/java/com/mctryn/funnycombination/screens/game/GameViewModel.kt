package com.mctryn.funnycombination.screens.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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
        val repositoryResult = gameRepository.itemClicked(resId)
        when (repositoryResult) {
            is CheckResult.ShouldShowEmptyCharacter -> {
                mutableStateFlow.value = GameUIState.RepeatSequenceState(
                    baseList = repositoryResult.baseItems,
                    chosenList = emptyList(),
                    score = repositoryResult.bestScore
                )
            }

            is CheckResult.ShouldShowNextCharacter -> {
                // TODO: find fix
                val newState = if (mutableStateFlow.value is GameUIState.RepeatSequenceState) {
                    (mutableStateFlow.value as GameUIState.RepeatSequenceState).copy(
                        baseList = repositoryResult.baseItems,
                        chosenList = repositoryResult.chosenItems,
                        score = repositoryResult.bestScore
                    )
                } else {
                    GameUIState.RepeatSequenceState(
                        baseList = repositoryResult.baseItems,
                        chosenList = repositoryResult.chosenItems,
                        score = repositoryResult.bestScore
                    )
                }
                mutableStateFlow.value = newState
            }

            is CheckResult.ShouldShowNextSequence -> {
                mutableStateFlow.value = GameUIState.ShowSequenceState(
                    repositoryResult.nextSequence
                )
            }

            is CheckResult.ShouldShowFail -> {
                mutableStateFlow.value = GameUIState.Result
            }
        }
    }


    fun tryAgain() {
        val result = gameRepository.tryAgain()
        mutableStateFlow.value = GameUIState.ShowSequenceState(result.nextSequence)
    }
}