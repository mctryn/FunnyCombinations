package com.mctryn.funnycombination.ui.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mctryn.funnycombination.R
import com.mctryn.funnycombination.data.Emojis
import com.mctryn.funnycombination.ui.components.BasicButton

@Composable
fun EnterSequence(
    chosenList: List<Int>,
    baseElements: List<Int>,
    onItemClicked: (Int) -> Unit,
    score: Int,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (myComposableRefBot) = createRefs()
        val (myComposableRefTop) = createRefs()
        val (myComposableRefCenter) = createRefs()
        Score(score, Modifier.constrainAs(myComposableRefTop) {
            top.linkTo(parent.top)
        })
        Display(chosenList, Modifier.constrainAs(myComposableRefCenter){
            centerVerticallyTo(parent)
            centerHorizontallyTo(parent)
        })
        Input(baseElements, onItemClicked, Modifier.constrainAs(myComposableRefBot) {
            bottom.linkTo(parent.bottom)
        })
    }
}

@Composable
fun Score(score: Int, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = "Score :$score")
    }
}

@Composable
fun Display(chosenList: List<Int>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stringResource(R.string.repeat_the_sequence))
        Spacer(modifier = modifier.padding(8.dp))
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
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        baseElements.forEach { element ->
            BasicButton(
                text = stringResource(element),
                onClick = { onItemClicked(element) },
                modifier = Modifier.weight(0.2F)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun EnterScreenPreview(
    chosenList: List<Int> = listOf(R.string.game_emoji_02),
    baseElements: List<Int> = Emojis().provideResources(),
    onItemClicked: (Int) -> Unit = {},
    score: Int = 1,
) {
    EnterSequence(
        chosenList, baseElements, onItemClicked, score
    )
}