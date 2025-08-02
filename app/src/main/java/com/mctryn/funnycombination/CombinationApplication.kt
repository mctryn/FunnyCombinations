package com.mctryn.funnycombination

import android.app.Application
import com.mctryn.funnycombination.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CombinationApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CombinationApplication)
            modules(androidModule)
        }
    }
}