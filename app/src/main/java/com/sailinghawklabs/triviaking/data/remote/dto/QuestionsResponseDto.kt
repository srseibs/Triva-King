package com.sailinghawklabs.triviaking.data.remote.dto

import com.google.gson.annotations.SerializedName

data class QuestionsResponseDto(

    @SerializedName("response_code")
    val responseCode: Int,

    val results: List<QuestionDto>

)
