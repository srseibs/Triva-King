package com.sailinghawklabs.triviaking.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CategoriesCountDto(
    @SerializedName("total_question_count")
    val totalQuestions: Int,

    @SerializedName("total_easy_question_count")
    val numEasy: Int,

    @SerializedName("total_medium_question_count")
    val numMedium: Int,

    @SerializedName("total_hard_question_count")
    val numHard: Int,
)
