package com.mctryn.funnycombination.screens.highscore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.koinViewModel
import java.text.DateFormat

@Composable
fun HighScoreScreen(
    highScoreViewModel: HighScoreViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val records = highScoreViewModel.getHighScore()
// TODO: Change date format

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        records.forEach { record ->
            Row(
                modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Date : ${record.data.toLocaleString()} ")
                Text(text = "Score : ${record.score}")
            }
        }
    }

}