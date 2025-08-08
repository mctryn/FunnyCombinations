package com.mctryn.funnycombination.screens.game

import com.mctryn.funnycombination.domain.CheckResult

class GameUiMapper : CheckResult.Mapper<GameUIState> {
    override fun mapNextCharacter(
        baseItems: List<Int>,
        chosenItems: List<Int>,
        bestScore: Int
    ): GameUIState {
        return GameUIState.RepeatSequenceState(
            baseList = baseItems.toList(),
            chosenList = chosenItems.toList(),
            score = bestScore
        )
    }

    override fun mapNextSequence(nextSequence: List<Int>): GameUIState {
        return GameUIState.ShowSequenceState(
            sequenceList = nextSequence
        )
    }

    override fun mapFail(
        savedToScoreBoard: Boolean,
        score: Int
    ): GameUIState {
        return GameUIState.Result(
            savedToScoreBoard = savedToScoreBoard,
            score = score
        )
    }
}