package com.mctryn.funnycombination.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mctryn.funnycombination.R

@Composable
fun GameScaffold(
    showBack: Boolean = false,
    onBackPressed: () -> Unit = { },
    content: @Composable() (PaddingValues) -> Unit,
) {
    BackHandler {
        onBackPressed.invoke()
    }
    Scaffold(topBar = {
        TopAppBar(
            stringResource(R.string.app_name),
            showBack,
            onBackPressed
        )
    }, content = { paddingValues ->
        content.invoke(paddingValues)
    })
}