package com.mctryn.funnycombination.screens.game

import com.mctryn.funnycombination.DataSource
import com.mctryn.funnycombination.Emojis
import kotlin.random.Random

class GameRepository(
    private val emojis: Emojis = Emojis(),
    private val dataSource: DataSource
) {

    private var bestScore = 0
    private var chosenCount = 0
    private var chosenList = mutableListOf<Int>()
    private var sequence = generateList(bestScore)
    private var baseElements = emojis.list.map { it.resId }

    fun getInitState() = CheckResult.ShouldShowNextCharacter(baseElements, emptyList(), bestScore)

    fun getInitialSequence(): List<Int> = sequence


    private fun generateList(length: Int): List<Int> {
        val result = mutableListOf<Int>()
        (0..length).forEach { i ->
            result += emojis.list[Random.nextInt(0, emojis.list.size)].resId
        }
        return result
    }

    fun itemClicked(resId: Int): CheckResult {
        if (sequence[chosenCount] == resId) {
            chosenList.add(resId)
            chosenCount++
            if (chosenList.size == sequence.size) {
                bestScore++
                sequence = generateList(bestScore)
                chosenList.clear()
                chosenCount = 0
                return CheckResult.ShouldShowNextSequence(sequence)
            }
            return CheckResult.ShouldShowNextCharacter(baseElements, chosenList.toList(), bestScore)
        } else {
            val saved = dataSource.saveIfMoreThanPrevious(bestScore)
            return CheckResult.ShouldShowFail(saved, bestScore)
        }
    }

    fun tryAgain(): CheckResult.ShouldShowNextSequence {
        bestScore = 0
        chosenCount = 0
        sequence = generateList(bestScore)
        chosenList.clear()
        return CheckResult.ShouldShowNextSequence(sequence)
    }

}

interface CheckResult {
    data class ShouldShowNextCharacter(
        val baseItems: List<Int>,
        val chosenItems: List<Int>,
        val bestScore: Int
    ) : CheckResult

    data class ShouldShowNextSequence(
        val nextSequence: List<Int>
    ) : CheckResult

    data class ShouldShowFail(
        val savedToScoreBoard: Boolean,
        val score: Int
    ) : CheckResult
}