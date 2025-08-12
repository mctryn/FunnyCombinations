package com.mctryn.funnycombination.ui.screens.spalsh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class SplashScreenViewModel() : ViewModel() {
    private val splashDelay: Long = 3000L
    private val mutableStateFlow = MutableStateFlow(true)
    val isLoading = mutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            delay(splashDelay)
            mutableStateFlow.value = false
        }
    }
}