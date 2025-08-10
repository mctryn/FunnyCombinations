package com.mctryn.funnycombination.ui.screens.highscore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mctryn.funnycombination.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HighScoreScreen(
    modifier: Modifier = Modifier,
    highScoreViewModel: HighScoreViewModel = koinViewModel()
) {
    val records = highScoreViewModel.state.collectAsStateWithLifecycle().value

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (records) {
            is HighScoreUiState.NoHighScoreState -> {
                Text(stringResource(R.string.no_high_scores_yet))
            }

            is HighScoreUiState.HighScoreState -> {
                records.scores.forEach { record ->
                    Text(
                        text = stringResource(
                            R.string.date,
                            record.data
                        ) + " " + stringResource(
                            R.string.score,
                            record.score
                        )
                    )
                }
            }
        }

    }

}