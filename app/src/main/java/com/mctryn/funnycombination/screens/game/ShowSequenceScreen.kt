package com.mctryn.funnycombination.screens.game

import android.content.Context
import android.icu.text.BreakIterator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun ShowSequenceScreen(
    sequence: List<Int>,
    modifier: Modifier = Modifier,
    onAnimationFinished: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedTypingText(
            text = prepareSequence(sequence, LocalContext.current),
            onAnimationFinished = onAnimationFinished
        )
    }
}

fun prepareSequence(sequence: List<Int>, context: Context) =
    sequence.joinToString("") { context.getString(it) }

@Composable
fun AnimatedTypingText(
    text: String,
    typingDelayInMs: Long = 1000L,
    onAnimationFinished: () -> Unit
) {
    var substringText by remember { mutableStateOf("") }
    LaunchedEffect(text) {
        val breakIterator = BreakIterator.getCharacterInstance()
        breakIterator.setText(text)
        var currentIdx = breakIterator.first()
        while (currentIdx != BreakIterator.DONE) {
            val nextIdx = breakIterator.next()
            if (nextIdx != BreakIterator.DONE) {
                substringText = text.substring(0, nextIdx)
                delay(typingDelayInMs)
            }
            currentIdx = nextIdx
        }
        onAnimationFinished.invoke()
    }
    Text(text = substringText, fontSize = 30.sp)
}