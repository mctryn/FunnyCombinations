package com.mctryn.funnycombination.data

import com.mctryn.funnycombination.data.storage.Record
import com.mctryn.funnycombination.data.storage.RecordDao
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ScoreBoardRepository(
    private val recordDao: RecordDao
) {
    suspend fun saveIfMoreThanPrevious(score: Int): Boolean {
        val allValues = getAllValues()
        if ((allValues.isEmpty() && score != 0) || allValues.last().score < score) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.now().format(formatter)
            recordDao.insert(Record(score, date))
            return true
        }
        return false
    }

    suspend fun getAllValues(): List<ScoreRecord> {
        return recordDao.getAll().map { ScoreRecord(it.date, it.score) }
    }
}

data class ScoreRecord(
    val data: String,
    val score: Int
)