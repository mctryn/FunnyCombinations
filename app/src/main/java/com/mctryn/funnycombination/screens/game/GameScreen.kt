package com.mctryn.funnycombination.screens.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameScreen(gameViewModel: GameViewModel = koinViewModel(), navController: NavController, modifier: Modifier = Modifier) {
    val state = gameViewModel.state.collectAsStateWithLifecycle().value
    val onAnimationFinished = { gameViewModel.animationFinished() }
    val onItemClicked = { resId: Int -> gameViewModel.itemClicked(resId) }
    val resultOnMainMenuClicked: () -> Unit = { navController.popBackStack() }
    val resultTryAgain = { gameViewModel.tryAgain() }

    when (state) {
        is GameUIState.ShowSequenceState -> ShowSequenceScreen(
            state.sequenceList,
            onAnimationFinished = onAnimationFinished
        )

        is GameUIState.RepeatSequenceState -> EnterSequence(
            chosenList = state.chosenList,
            baseElements = state.baseList,
            score = state.score,
            onItemClicked = onItemClicked
        )

        is GameUIState.Result -> FailScreen(resultOnMainMenuClicked, resultTryAgain)
    }
}




