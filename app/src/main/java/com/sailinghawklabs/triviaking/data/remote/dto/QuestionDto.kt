package com.sailinghawklabs.triviaking.data.remote.dto

import com.google.gson.annotations.SerializedName

data class QuestionDto(
    val category: String,
    val type: String,
    val question: String,
    val difficulty: String,

    @SerializedName("correct_answer")
    val correctAnswer: String,

    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>
)
