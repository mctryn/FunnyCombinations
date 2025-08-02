package com.mctryn.funnycombination.screens.main

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val mainItems: MainItemsProvider = MainItemsProvider()
): ViewModel() {
    fun getMenuItems() = mainItems.mainMenuItems
}