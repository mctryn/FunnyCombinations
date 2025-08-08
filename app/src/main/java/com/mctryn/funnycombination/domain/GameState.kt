package com.mctryn.funnycombination.domain

data class GameState(
    val bestScore: Int,
    val chosenCount: Int,
    val chosenList: List<Int>,
    val sequence: List<Int>,
    val baseElements: List<Int>,
)