package com.mctryn.funnycombination.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinViewModel(),
    navController: NavController
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        mainViewModel.getMenuItems().forEach { item ->
            MainScreenItem(
                onClick = { item.second.invoke(navController) },
                label = stringResource(item.first)
            )
        }
    }
}

@Composable
fun MainScreenItem(onClick: () -> Unit, label: String, modifier: Modifier = Modifier) {
    Button(onClick, modifier.fillMaxWidth()) {
        Text(label)
    }
}

@Preview
@Composable
private fun MainScreenMenuItemPreview() {
    MainScreenItem({ }, "Game")
}