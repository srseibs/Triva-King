package com.sailinghawklabs.triviaking.presentation.quiz

import com.sailinghawklabs.triviaking.domain.model.Question

data class QuizGameState(
    val quiz: List<Question> = emptyList(),
    val currentQuestionNumber: Int = -1,
    val numCorrect: Int = 0,
    val numWrong: Int = 0,
)

fun QuizGameState.isDone():Boolean  = (currentQuestionNumber >= numQuestions())
fun QuizGameState.numQuestions():Int = quiz.size