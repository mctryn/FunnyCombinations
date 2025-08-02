package com.mctryn.funnycombination

import java.util.Date

class DataSource {
    fun saveIfMoreThanPrevious(score: Int) {
        if (scoreMap.isEmpty() || scoreMap.last().score < score) {
            scoreMap.add(ScoreRecord(Date(), score))
        }
    }

    fun getAllValues(): List<ScoreRecord> {
        return scoreMap
    }

    private val scoreMap: MutableList<ScoreRecord> = mutableListOf()


}

data class ScoreRecord(
    val data: Date,
    val score: Int
)