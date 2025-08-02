package com.mctryn.funnycombination.screens.highscore

import androidx.lifecycle.ViewModel
import com.mctryn.funnycombination.DataSource
import com.mctryn.funnycombination.ScoreRecord

class HighScoreViewModel(
    private val dataSource: DataSource
) : ViewModel() {
    fun getHighScore(): List<ScoreRecord> {
        return dataSource.getAllValues().sortedByDescending { it.score }
    }
}