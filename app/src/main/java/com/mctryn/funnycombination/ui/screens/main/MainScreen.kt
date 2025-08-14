package com.mctryn.funnycombination.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.mctryn.funnycombination.R
import com.mctryn.funnycombination.ui.components.BasicButton
import com.mctryn.funnycombination.ui.components.TopAppBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinViewModel(),
    navController: NavController
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    Scaffold(topBar = {
        TopAppBar(stringResource(R.string.app_name))
    }, content = { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            mainViewModel.getMenuItems().forEach { item ->
                MainScreenItem(
                    onClick = { item.second.invoke(navController, lifecycleOwner) },
                    label = stringResource(item.first)
                )
            }
        }
    })
}

@Composable
fun MainScreenItem(onClick: () -> Unit, label: String) {
    BasicButton(text = label, onClick = onClick)
}

@Preview
@Composable
private fun MainScreenMenuItemPreview() {
    MainScreenItem({ }, "Game")
}