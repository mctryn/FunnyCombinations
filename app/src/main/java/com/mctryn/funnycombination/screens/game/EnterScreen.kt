package com.mctryn.funnycombination.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mctryn.funnycombination.R

@Composable
fun EnterSequence(
    chosenList: List<Int>,
    baseElements: List<Int>,
    onItemClicked: (Int) -> Unit,
    score: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = modifier)
        Display(chosenList, score, modifier)
        Input(baseElements, onItemClicked, modifier)
    }
}

@Composable
fun Display(chosenList: List<Int>, score: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Score :$score")
        Spacer(modifier = modifier)
        Text(stringResource(R.string.repeat_the_sequence))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            chosenList.forEach { Text(text = stringResource(it), fontSize = 30.sp) }
        }
    }
}

@Composable
fun Input(
    baseElements: List<Int>,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        baseElements.forEach { element ->
            Button({ onItemClicked(element) }, modifier.weight(1F)) {
                Text(text = stringResource(element), fontSize = 30.sp)
            }
        }

    }
}