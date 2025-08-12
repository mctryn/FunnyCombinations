package com.mctryn.funnycombination.ui.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mctryn.funnycombination.R
import com.mctryn.funnycombination.ui.components.BasicButton

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
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.your_result, score))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(R.string.wrong_answer))
            if (savedToScoreBoard) {
                Text(stringResource(R.string.resul_is_saved_to_score_board))
            }
        }

        Column {
            BasicButton(Modifier, stringResource(R.string.try_again), resultTryAgain)
            BasicButton(Modifier, stringResource(R.string.main_menu), resultOnMainMenuClicked)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FailScreenPreview(
    modifier: Modifier = Modifier,
    savedToScoreBoard: Boolean = true,
    score: Int = 1,
    resultOnMainMenuClicked: () -> Unit = { },
    resultTryAgain: () -> Unit = { }
) {
    FailScreen(
        modifier, savedToScoreBoard, score, resultOnMainMenuClicked, resultTryAgain
    )
}