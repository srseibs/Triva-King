package com.sailinghawklabs.triviaking.domain.model

data class Question(
    val category: String,
    val difficulty: DIFFICULTY,
    val question: String,
    val answers: List<String>,
    val correctAnswer: String,
)
