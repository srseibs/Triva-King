package com.sailinghawklabs.triviaking.ui.screen.quiz

import com.sailinghawklabs.triviaking.domain.model.Question

data class QuizOverallState(
    val screenState: QuizScreenState = QuizScreenState(),
    val listOfQuestions: List<Question> = emptyList(),
    val currentQuestionNumber: Int = -1,
    val numCorrect: Int = 0,
    val numWrong: Int = 0,
)