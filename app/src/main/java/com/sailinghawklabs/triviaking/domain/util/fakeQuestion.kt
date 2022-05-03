package com.sailinghawklabs.triviaking.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.sailinghawklabs.triviaking.domain.model.Question

@Composable
fun fakeQuestion(): Question {

    return Question(
        category = "Dumplings: Lots of fat and starch, lasjlasdasd alskhj asdasldkj dc",
        difficulty = "Easy",
        question = getLoremIpsum(words = 12),
        answers = listOf(
            getLoremIpsum(words = 2),
            getLoremIpsum(words = 4),
            getLoremIpsum(words = 15),
        ),
        correctAnswer = 1,
    )
}

@Composable
fun getLoremIpsum(words: Int): String {
    return LoremIpsum(words).values.first()
}
