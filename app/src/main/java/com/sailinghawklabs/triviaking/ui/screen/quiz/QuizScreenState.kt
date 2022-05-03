package com.sailinghawklabs.triviaking.ui.screen.quiz

import com.sailinghawklabs.triviaking.ui.theme.util.TriBoxState

data class QuizScreenState(
    val questionNumber: Int = 0,
    val numberOfQuestions: Int = 0,
    val numCorrect: Int = 0,
    val question: String  = "",
    val category: String = "",
    val difficulty: String = "",
    val answers: List<String> = emptyList(),
    val correctAnswer: Int = -1,
    val answerState: List<TriBoxState> = emptyList(),
)
