package com.mctryn.funnycombination.ui.screens.game

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mctryn.funnycombination.R
import com.mctryn.funnycombination.ui.components.TopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = koinViewModel(),
    navController: NavController
) {
    val state = gameViewModel.state.collectAsStateWithLifecycle().value
    val onAnimationFinished = { gameViewModel.animationFinished() }
    val onItemClicked = { resId: Int -> gameViewModel.itemClicked(resId) }
    val resultOnMainMenuClicked: () -> Unit = { navController.popBackStack() }
    val resultTryAgain = { gameViewModel.tryAgain() }

    Scaffold(
        topBar = {
            TopAppBar(
                stringResource(R.string.app_name),
                true,
                { navController.popBackStack() })
        },
        content = { padding ->

            when (state) {
                is GameUIState.ShowSequenceState -> ShowSequenceScreen(
                    modifier = modifier.padding((padding)),
                    sequence = state.sequenceList,
                    onAnimationFinished = onAnimationFinished
                )

                is GameUIState.RepeatSequenceState -> EnterSequence(
                    modifier = modifier.padding((padding)),
                    chosenList = state.chosenList,
                    baseElements = state.baseList,
                    score = state.score,
                    onItemClicked = onItemClicked
                )

                is GameUIState.Result -> FailScreen(
                    modifier = modifier.padding((padding)),
                    savedToScoreBoard = state.savedToScoreBoard,
                    score = state.score,
                    resultOnMainMenuClicked = resultOnMainMenuClicked,
                    resultTryAgain = resultTryAgain
                )
            }
        })
}




