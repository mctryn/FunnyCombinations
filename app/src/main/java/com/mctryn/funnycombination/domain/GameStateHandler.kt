package com.mctryn.funnycombination.domain

import kotlin.random.Random

interface GameStateHandler {

    fun provideState(): GameState

    fun generateNewSequence(baseElements: List<Int>, count: Int = 0): List<Int> {
        val resultSequence = mutableListOf<Int>()
        (0..count).forEach { i ->
            resultSequence.add(
                baseElements[Random.nextInt(
                    0,
                    baseElements.size
                )]
            )
        }
        return resultSequence
    }

    class NewGameState(private val baseElements: List<Int>) :
        GameStateHandler {
        override fun provideState() = GameState(
            bestScore = 0,
            chosenCount = 0,
            chosenList = emptyList(),
            baseElements = baseElements,
            sequence = generateNewSequence(baseElements)
        )

    }

    class SuccessGameState(private val gameState: GameState) :
        GameStateHandler {
        override fun provideState(): GameState {
            val bestScore = gameState.bestScore + 1
            return GameState(
                bestScore = bestScore,
                chosenCount = 0,
                chosenList = emptyList(),
                baseElements = gameState.baseElements,
                sequence = generateNewSequence(gameState.baseElements, bestScore)
            )
        }
    }

    class CorrectItemChosenState(private val gameState: GameState) :
        GameStateHandler {
        override fun provideState(): GameState {
            val chosenCount = gameState.chosenCount + 1
            val chosenList = gameState.chosenList.toMutableList()
            chosenList.add(gameState.sequence[gameState.chosenCount])
            return GameState(
                bestScore = gameState.bestScore,
                chosenCount = chosenCount,
                chosenList = chosenList,
                baseElements = gameState.baseElements,
                sequence = gameState.sequence
            )
        }
    }
}