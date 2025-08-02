package com.mctryn.funnycombination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mctryn.funnycombination.navigation.AppNavigation
import com.mctryn.funnycombination.screens.spalsh.SplashScreenViewModel
import com.mctryn.funnycombination.ui.theme.FunnyCombinationTheme
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    private val viewModel: SplashScreenViewModel by inject<SplashScreenViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashscreen.setKeepOnScreenCondition { viewModel.isLoading.value }
        setContent {
            FunnyCombinationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}