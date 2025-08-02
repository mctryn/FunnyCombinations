package com.mctryn.funnycombination.screens.game

interface GameUIState {
    data class ShowSequenceState(
        val sequenceList: List<Int>
    ) : GameUIState

    data class RepeatSequenceState(
        val baseList: List<Int>,
        val chosenList: List<Int>,
        val score: Int
    ) : GameUIState

    data class Result(
        val savedToScoreBoard: Boolean,
        val score: Int
    ) : GameUIState
}