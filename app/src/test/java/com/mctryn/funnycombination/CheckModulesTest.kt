package com.mctryn.funnycombination

import com.mctryn.funnycombination.di.androidModule
import org.junit.Test
import org.koin.android.test.verify.androidVerify
import org.koin.test.KoinTest


class CheckModulesTest : KoinTest {

    @Test
    fun checkAllModules(){
        androidModule.androidVerify()
    }
}