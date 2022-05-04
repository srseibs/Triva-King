package com.sailinghawklabs.triviaking.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CategoriesResponseDto(
    @SerializedName("trivia_categories")
    val triviaCategories: List<TriviaCategoryDto>
)
