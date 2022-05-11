package com.sailinghawklabs.triviaking.presentation.quiz

import com.sailinghawklabs.triviaking.domain.model.Question

data class QuizOverallState(
    val quiz: List<Question> = emptyList(),
    val currentQuestionNumber: Int = -1,
    val numCorrect: Int = 0,
    val numWrong: Int = 0,
)