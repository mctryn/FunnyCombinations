package com.mctryn.funnycombination.ui.screens.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
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

    when (state) {
        is GameUIState.ShowSequenceState -> ShowSequenceScreen(
            modifier = modifier,
            sequence = state.sequenceList,
            onAnimationFinished = onAnimationFinished
        )

        is GameUIState.RepeatSequenceState -> EnterSequence(
            modifier = modifier,
            chosenList = state.chosenList,
            baseElements = state.baseList,
            score = state.score,
            onItemClicked = onItemClicked
        )

        is GameUIState.Result -> FailScreen(
            modifier = modifier,
            savedToScoreBoard = state.savedToScoreBoard,
            score = state.score,
            resultOnMainMenuClicked = resultOnMainMenuClicked,
            resultTryAgain = resultTryAgain
        )
    }
}




