package com.sailinghawklabs.triviaking.ui.screen.quiz

import com.sailinghawklabs.triviaking.domain.model.Question

data class QuizScreenState(
    val question: Question,
    val selectedAnswer: Int,
)
