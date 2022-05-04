package com.sailinghawklabs.triviaking.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.sailinghawklabs.triviaking.domain.model.Question

@Composable
fun fakeQuestion(): Question {

    return Question(
        category = "Entertainment: Video Games",
        difficulty = "Easy",
//        question = getLoremIpsum(words = 12),
        question = "In &quot;Overwatch,&quot; what is the hero McCree&#039;s full name?",
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
