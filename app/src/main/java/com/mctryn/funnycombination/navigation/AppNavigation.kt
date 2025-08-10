package com.mctryn.funnycombination.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mctryn.funnycombination.ui.screens.game.GameScreen
import com.mctryn.funnycombination.ui.screens.highscore.HighScoreScreen
import com.mctryn.funnycombination.ui.screens.main.MainScreen
import com.mctryn.funnycombination.ui.screens.privacy.PrivacyScreen
import kotlinx.serialization.Serializable


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Main) {
        composable<Main> { MainScreen(navController = navController, modifier = modifier) }
        composable<Game> { GameScreen(navController = navController, modifier = modifier) }
        composable<HighScore> { HighScoreScreen(navController = navController, modifier = modifier) }
        composable<Privacy> { PrivacyScreen(navController = navController, modifier = modifier) }
        composable<Exit> {
            val context = LocalContext.current
            if (context is Activity) context.finish()
        }
    }
}


@Serializable
object Main

@Serializable
object Game

@Serializable
object HighScore

@Serializable
object Privacy

@Serializable
object Exit


