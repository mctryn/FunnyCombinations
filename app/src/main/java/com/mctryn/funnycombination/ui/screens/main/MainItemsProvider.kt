package com.mctryn.funnycombination.ui.screens.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.mctryn.funnycombination.R
import com.mctryn.funnycombination.navigation.Exit
import com.mctryn.funnycombination.navigation.Game
import com.mctryn.funnycombination.navigation.HighScore
import com.mctryn.funnycombination.navigation.Privacy

class MainItemsProvider {

    val mainMenuItems: List<Pair<Int, (navController: NavController, lifecycleOwner: LifecycleOwner) -> Unit>> =
        listOf(
            Pair(R.string.main_game) { navController, lifecycleOwner ->
                navigate(
                    lifecycleOwner,
                    navController,
                    destination = Game
                )
            },
            Pair(R.string.main_score) { navController, lifecycleOwner ->
                navigate(
                    lifecycleOwner,
                    navController,
                    destination = HighScore
                )
            },
            Pair(R.string.main_privacy) { navController, lifecycleOwner ->
                navigate(
                    lifecycleOwner,
                    navController,
                    destination = Privacy
                )
            },
            Pair(R.string.main_exit) { navController, lifecycleOwner ->
                navigate(
                    lifecycleOwner,
                    navController,
                    destination = Exit
                )
            },
        )

    private fun <T : Any> navigate(
        lifecycleOwner: LifecycleOwner,
        navController: NavController,
        destination: T
    ) {
        val currentState = lifecycleOwner.lifecycle.currentState
        if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            navController.navigate(destination)
        }
    }
}