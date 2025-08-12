package com.mctryn.funnycombination.domain

interface CheckResult {
    val gameState: GameState

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun mapNextCharacter(
            baseItems: List<Int>,
            chosenItems: List<Int>,
            bestScore: Int
        ): T

        fun mapNextSequence(nextSequence: List<Int>): T

        fun mapFail(savedToScoreBoard: Boolean, score: Int): T
    }

    data class ShouldShowNextCharacter(
        override val gameState: GameState,
        val baseItems: List<Int> = gameState.baseElements,
        val chosenItems: List<Int> = gameState.chosenList,
        val bestScore: Int = gameState.bestScore
    ) : CheckResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapNextCharacter(baseItems, chosenItems, bestScore)
        }
    }

    data class ShouldShowNextSequence(
        override val gameState: GameState,
        val nextSequence: List<Int> = gameState.sequence
    ) : CheckResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapNextSequence(nextSequence)
        }
    }

    data class ShouldShowFail(
        override val gameState: GameState,
        val savedToScoreBoard: Boolean,
        val score: Int,
    ) : CheckResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapFail(savedToScoreBoard, score)
        }
    }
}