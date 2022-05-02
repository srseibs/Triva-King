package com.sailinghawklabs.triviaking.ui.screen.quiz

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.sailinghawklabs.triviaking.domain.model.Question

@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel()
) {
    val question = Question(
        category = "Birds",
        difficulty = "Easy",
        question = "What is the air speed of a swallow",
        correctAnswer = 0,
        answers = listOf(
            "Fully laden?",
            "0.9c",
            "They cannot fly"
        )
    )
    val viewState = QuizScreenState(
        question = question,
        selectedAnswer = 2,
    )
    QuizScreenContent(viewState = viewState)

}