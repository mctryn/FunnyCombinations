package com.mctryn.funnycombination.ui.screens.privacy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mctryn.funnycombination.R
import com.mctryn.funnycombination.ui.components.TopAppBar

@Composable
fun PrivacyScreen(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                stringResource(R.string.app_name),
                true,
                onBackPressed
            )
        },
        content = { padding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Text(stringResource(R.string.policy))
            }
        }
    )

}
