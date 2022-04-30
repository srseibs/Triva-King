package com.sailinghawklabs.triviaking.domain.model

data class Question(
    val category: String,
    val difficulty: String,
    val question: String,
    val answers: List<String>,
    val correctAnswer: Int,
)
