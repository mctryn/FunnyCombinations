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
            is CheckResult.ShouldShowNextCharacter -> {
                mutableStateFlow.value =  GameUIState.RepeatSequenceState(
                        baseList = repositoryResult.baseItems.toList(),
                        chosenList = repositoryResult.chosenItems.toList(),
                        score = repositoryResult.bestScore
                    )
                }

            is CheckResult.ShouldShowNextSequence -> {
                mutableStateFlow.value = GameUIState.ShowSequenceState(
                    repositoryResult.nextSequence
                )
            }

            is CheckResult.ShouldShowFail -> {
                mutableStateFlow.value = GameUIState.Result(repositoryResult.savedToScoreBoard, repositoryResult.score)
            }
        }
    }


    fun tryAgain() {
        val result = gameRepository.tryAgain()
        mutableStateFlow.value = GameUIState.ShowSequenceState(result.nextSequence)
    }
}