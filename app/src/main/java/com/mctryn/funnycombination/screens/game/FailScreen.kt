package com.mctryn.funnycombination.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mctryn.funnycombination.R

@Composable
fun FailScreen(
    modifier: Modifier = Modifier,
    savedToScoreBoard: Boolean,
    score: Int,
    resultOnMainMenuClicked: () -> Unit,
    resultTryAgain: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (savedToScoreBoard) {
            Text(stringResource(R.string.resul_is_saved_to_score_board))
        }
        Text(stringResource(R.string.your_result, score))
        Text(stringResource(R.string.wrong_answer))
        Button(onClick = resultTryAgain, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(stringResource(R.string.try_again))
        }
        Button(onClick = resultOnMainMenuClicked, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(stringResource(R.string.main_menu))
        }
    }
}