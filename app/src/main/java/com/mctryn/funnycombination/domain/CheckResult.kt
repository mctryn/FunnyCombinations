package com.mctryn.funnycombination.domain

interface CheckResult {
    val gameState: GameState

    data class ShouldShowNextCharacter(
        override val gameState: GameState,
        val baseItems: List<Int> = gameState.baseElements,
        val chosenItems: List<Int> = gameState.chosenList,
        val bestScore: Int = gameState.bestScore
    ) : CheckResult

    data class ShouldShowNextSequence(
        override val gameState: GameState,
        val nextSequence: List<Int> = gameState.sequence
    ) : CheckResult

    data class ShouldShowFail(
        override val gameState: GameState,
        val savedToScoreBoard: Boolean,
        val score: Int = gameState.bestScore,
    ) : CheckResult
}