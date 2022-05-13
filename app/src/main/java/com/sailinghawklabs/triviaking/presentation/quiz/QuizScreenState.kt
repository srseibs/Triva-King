package com.sailinghawklabs.triviaking.presentation.quiz

import com.sailinghawklabs.triviaking.domain.model.Question
import com.sailinghawklabs.triviaking.ui.theme.util.TriBoxState

data class QuizScreenState(
    val questionNumberDisplay: Int = 0,
    val numberOfQuestions: Int = 0,
    val numCorrect: Int = 0,
    val question: String  = "",
    val category: String = "",
    val difficulty: String = "",
    val answers: List<String> = emptyList(),
    val correctAnswerIndex: Int = -1,

    var answerBoxes: List<TriBoxState> = emptyList(),
    val answersEnabled: Boolean = false,

    val continueEnabled: Boolean = false,
    val gameOverEnabled: Boolean = false,
)

