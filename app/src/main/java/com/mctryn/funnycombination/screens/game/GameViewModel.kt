package com.mctryn.funnycombination.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mctryn.funnycombination.domain.CheckResult
import com.mctryn.funnycombination.domain.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameRepository: GameRepository,
    private val mapper: CheckResult.Mapper<GameUIState>
) : ViewModel() {

    private val mutableStateFlow: MutableStateFlow<GameUIState> =
        MutableStateFlow(gameRepository.getInitialState().map(mapper))
    val state = mutableStateFlow.asStateFlow()

    fun animationFinished() {
        val afterAnimation =
            gameRepository.onAnimationFinished()
        mutableStateFlow.value = afterAnimation.map(mapper)
    }

    fun itemClicked(resId: Int) {
        viewModelScope.launch {
            val checkResult = gameRepository.itemClicked(resId)
            mutableStateFlow.value = checkResult.map(mapper)
        }
    }


    fun tryAgain() {
        val result = gameRepository.tryAgain()
        mutableStateFlow.value = GameUIState.ShowSequenceState(result.nextSequence)
    }
}