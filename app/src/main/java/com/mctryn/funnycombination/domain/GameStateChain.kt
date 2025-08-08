package com.mctryn.funnycombination.domain

import com.mctryn.funnycombination.data.ScoreBoardRepository

interface GameStateChain {

    suspend fun check(resId: Int, gameState: GameState): CheckResult


    class LastItemChosenChain(
        private val nextInChain: GameStateChain
    ) : GameStateChain {
        override suspend fun check(
            resId: Int,
            gameState: GameState
        ): CheckResult {
            if (gameState.chosenList.size + 1 == gameState.sequence.size && gameState.sequence[gameState.chosenCount] == resId) {
                val currentGameState = GameStateHandler.SuccessGameState(gameState).provideState()
                return CheckResult.ShouldShowNextSequence(currentGameState)
            }
            return nextInChain.check(resId, gameState)
        }

    }

    class CorrectItemChosenChain(
        private val nextInChain: GameStateChain
    ) : GameStateChain {
        override suspend fun check(
            resId: Int,
            gameState: GameState
        ): CheckResult {
            if (gameState.sequence[gameState.chosenCount] == resId) {
                val currentGameState =
                    GameStateHandler.CorrectItemChosenState(gameState).provideState()
                return CheckResult.ShouldShowNextCharacter(
                    currentGameState
                )
            } else {
                return nextInChain.check(resId, gameState)
            }

        }

    }

    class IncorrectItemChosenChain(
        private val scoreBoardRepository: ScoreBoardRepository
    ) : GameStateChain {
        override suspend fun check(
            resId: Int,
            gameState: GameState
        ): CheckResult {
            val saved = scoreBoardRepository.saveIfMoreThanPrevious(gameState.bestScore)
            val currentGameState =
                GameStateHandler.NewGameState(gameState.baseElements).provideState()
            return CheckResult.ShouldShowFail(currentGameState, saved)
        }

    }
}