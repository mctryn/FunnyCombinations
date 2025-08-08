package com.mctryn.funnycombination.domain

import kotlin.random.Random

interface GameStateHandler {

    fun provideState(): GameState

    fun generateSequence(baseElements: List<Int>, count: Int = 0): List<Int> {
        return listOf(
            baseElements[Random.nextInt(
                0,
                baseElements.size
            )]
        )
    }

    class NewGameState(private val baseElements: List<Int>) :
        GameStateHandler {
        override fun provideState() = GameState(
            bestScore = 0,
            chosenCount = 0,
            chosenList = emptyList(),
            baseElements = baseElements,
            sequence = generateSequence(baseElements)
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
                sequence = generateSequence(gameState.baseElements, bestScore)
            )
        }
    }

    class CorrectItemChosenState(private val gameState: GameState) :
        GameStateHandler {
        override fun provideState(): GameState {
            val chosenCount = gameState.chosenCount + 1
            val chosenList = gameState.chosenList.toMutableList()
            chosenList.add(gameState.sequence[gameState.chosenCount + 1])
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