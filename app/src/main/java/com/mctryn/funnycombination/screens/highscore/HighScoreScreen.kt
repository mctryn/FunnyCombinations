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
import androidx.compose.ui.res.stringResource
import com.mctryn.funnycombination.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HighScoreScreen(
    modifier: Modifier = Modifier,
    highScoreViewModel: HighScoreViewModel = koinViewModel()
) {
    val records = highScoreViewModel.getHighScore()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (records.isEmpty()){
            Text(stringResource(R.string.no_high_scores_yet))
        }
        records.forEach { record ->
            Row(
                modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.date, record.data))
                Text(text = stringResource(R.string.score, record.score))
            }
        }
    }

}