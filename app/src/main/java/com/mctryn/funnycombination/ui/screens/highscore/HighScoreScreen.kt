package com.mctryn.funnycombination.ui.screens.highscore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mctryn.funnycombination.R
import com.mctryn.funnycombination.ui.components.TopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HighScoreScreen(
    modifier: Modifier = Modifier,
    highScoreViewModel: HighScoreViewModel = koinViewModel(),
    onBackPressed: () -> Unit
) {
    val records = highScoreViewModel.state.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            TopAppBar(
                stringResource(R.string.app_name),
                true,
                onBackPressed
            )
        },
        content = { padding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding),
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
        })
}