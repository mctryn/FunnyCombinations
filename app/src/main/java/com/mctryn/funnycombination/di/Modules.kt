package com.mctryn.funnycombination.di

import androidx.room.Room
import com.mctryn.funnycombination.data.Emojis
import com.mctryn.funnycombination.data.ScoreBoardRepository
import com.mctryn.funnycombination.data.storage.RecordDao
import com.mctryn.funnycombination.data.storage.RecordDatabase
import com.mctryn.funnycombination.screens.game.GameRepository
import com.mctryn.funnycombination.screens.game.GameViewModel
import com.mctryn.funnycombination.screens.highscore.HighScoreViewModel
import com.mctryn.funnycombination.screens.main.MainItemsProvider
import com.mctryn.funnycombination.screens.main.MainViewModel
import com.mctryn.funnycombination.screens.spalsh.SplashScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val androidModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            RecordDatabase::class.java,
            "record_db"
        ).build()
    }
    single<RecordDao> {
        val database = get<RecordDatabase>()
        database.recordDao()
    }

    singleOf(::ScoreBoardRepository)
    singleOf(::Emojis)
    singleOf(::MainItemsProvider)
    factoryOf(::GameRepository)

    factoryOf(::GameViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::HighScoreViewModel)
}