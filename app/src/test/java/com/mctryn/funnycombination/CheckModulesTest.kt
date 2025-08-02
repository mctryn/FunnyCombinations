package com.mctryn.funnycombination

import com.mctryn.funnycombination.di.androidModule
import org.junit.Test
import org.koin.android.test.verify.androidVerify
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest


class CheckModulesTest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkAllModules(){
        androidModule.androidVerify()
    }
}