package com.mctryn.funnycombination.navigation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mctryn.funnycombination.screens.game.GameScreen
import com.mctryn.funnycombination.screens.highscore.HighScoreScreen
import com.mctryn.funnycombination.screens.main.MainScreen
import com.mctryn.funnycombination.screens.privacy.PrivacyScreen
import kotlinx.serialization.Serializable

@SuppressLint("ContextCastToActivity")
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Main) {
        composable<Main> { MainScreen(navController = navController) }
        composable<Game> { GameScreen(navController = navController) }
        composable<HighScore> { HighScoreScreen() }
        composable<Privacy> { PrivacyScreen() }
        composable<Exit> { (LocalContext.current as? Activity)?.finish() }
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


