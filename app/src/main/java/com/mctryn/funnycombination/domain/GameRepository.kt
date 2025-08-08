package com.mctryn.funnycombination.domain

import com.mctryn.funnycombination.data.Emojis

class GameRepository(
    private val emojis: Emojis = Emojis(),
    private val gameStateChain: GameStateChain
) {
    private var currentGameState =
        GameStateHandler.NewGameState(emojis.provideResources()).provideState()


    fun onAnimationFinished() = CheckResult.ShouldShowNextCharacter(currentGameState)

    fun getInitialState(): CheckResult = CheckResult.ShouldShowNextSequence(currentGameState)

    suspend fun itemClicked(resId: Int): CheckResult {
        val result = gameStateChain.check(resId, currentGameState)
        currentGameState = result.gameState
        return result
    }

    fun tryAgain(): CheckResult.ShouldShowNextSequence {
        currentGameState = GameStateHandler.NewGameState(emojis.provideResources()).provideState()
        return CheckResult.ShouldShowNextSequence(currentGameState)
    }

}