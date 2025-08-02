package com.mctryn.funnycombination

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DataSource {
    fun saveIfMoreThanPrevious(score: Int): Boolean {
        if ((scoreMap.isEmpty() && score != 0) || scoreMap.last().score < score) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.now().format(formatter)
            scoreMap.add(ScoreRecord(date, score))
            return true
        }
        return false
    }

    fun getAllValues(): List<ScoreRecord> {
        return scoreMap
    }

    private val scoreMap: MutableList<ScoreRecord> = mutableListOf()


}

data class ScoreRecord(
    val data: String,
    val score: Int
)