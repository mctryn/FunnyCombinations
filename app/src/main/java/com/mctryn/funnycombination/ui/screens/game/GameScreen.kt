package com.mctryn.funnycombination.ui.screens.game

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mctryn.funnycombination.ui.components.GameScaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = koinViewModel(),
    onBackPressed: () -> Unit
) {
    val state = gameViewModel.state.collectAsStateWithLifecycle().value
    val onAnimationFinished = { gameViewModel.animationFinished() }
    val onItemClicked = { resId: Int -> gameViewModel.itemClicked(resId) }
    val resultOnMainMenuClicked: () -> Unit = onBackPressed
    val resultTryAgain = { gameViewModel.tryAgain() }

    GameScaffold(showBack = true, onBackPressed = onBackPressed) { padding ->
        when (state) {
            is GameUIState.ShowSequenceState -> ShowSequenceScreen(
                modifier = Modifier.padding((padding)),
                sequence = state.sequenceList,
                onAnimationFinished = onAnimationFinished
            )

            is GameUIState.RepeatSequenceState -> EnterSequence(
                modifier = Modifier.padding((padding)),
                chosenList = state.chosenList,
                baseElements = state.baseList,
                score = state.score,
                onItemClicked = onItemClicked
            )

            is GameUIState.Result -> FailScreen(
                modifier = Modifier.padding((padding)),
                savedToScoreBoard = state.savedToScoreBoard,
                score = state.score,
                resultOnMainMenuClicked = resultOnMainMenuClicked,
                resultTryAgain = resultTryAgain
            )
        }
    }
}




