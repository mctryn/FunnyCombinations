package com.mctryn.funnycombination.data

import com.mctryn.funnycombination.R

data class Emoji(
    val id: Int,
    val resId: Int
)

class Emojis() {
    val list: List<Emoji> = listOf(
        Emoji(id = 0, R.string.game_emoji_01),
        Emoji(id = 1, R.string.game_emoji_02),
        Emoji(id = 2, R.string.game_emoji_03),
        Emoji(id = 3, R.string.game_emoji_04),
        Emoji(id = 4, R.string.game_emoji_05),
    )
}