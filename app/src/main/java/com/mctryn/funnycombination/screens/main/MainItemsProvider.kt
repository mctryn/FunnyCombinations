package com.mctryn.funnycombination.screens.main

import androidx.navigation.NavController
import com.mctryn.funnycombination.R
import com.mctryn.funnycombination.navigation.Exit
import com.mctryn.funnycombination.navigation.Game
import com.mctryn.funnycombination.navigation.HighScore
import com.mctryn.funnycombination.navigation.Privacy

class MainItemsProvider {

    val mainMenuItems: List<Pair<Int, (navController: NavController) -> Unit>> = listOf(
        Pair(R.string.main_game) { navController -> navController.navigate(Game) },
        Pair(R.string.main_score) { navController -> navController.navigate(HighScore) },
        Pair(R.string.main_privacy) { navController -> navController.navigate(Privacy) },
        Pair(R.string.main_exit) { navController -> navController.navigate(Exit) },
    )
}