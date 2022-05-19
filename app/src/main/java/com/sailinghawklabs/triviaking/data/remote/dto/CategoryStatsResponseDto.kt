package com.sailinghawklabs.triviaking.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CategoryStatsResponseDto(

//    @SerializedName("category_id")
//    val categoryId: Int,
    @SerializedName("category_question_count")
    val statistics: CategoriesCountDto

)
