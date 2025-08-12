package com.mctryn.funnycombination.ui.screens.main

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val mainItems: MainItemsProvider = MainItemsProvider()
): ViewModel() {
    fun getMenuItems() = mainItems.mainMenuItems
}