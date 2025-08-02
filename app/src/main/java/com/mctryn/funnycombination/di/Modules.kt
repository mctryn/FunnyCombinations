package com.mctryn.funnycombination.di

import com.mctryn.funnycombination.DataSource
import com.mctryn.funnycombination.Emojis
import com.mctryn.funnycombination.screens.game.GameRepository
import com.mctryn.funnycombination.screens.game.GameViewModel
import com.mctryn.funnycombination.screens.highscore.HighScoreViewModel
import com.mctryn.funnycombination.screens.main.MainItemsProvider
import com.mctryn.funnycombination.screens.main.MainViewModel
import com.mctryn.funnycombination.screens.spalsh.SplashScreenViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val androidModule = module {
    singleOf(::DataSource)
    singleOf(::Emojis)
    singleOf(::MainItemsProvider)
    factoryOf(::GameRepository)

    factoryOf(::GameViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::HighScoreViewModel)
}