package com.sailinghawklabs.triviaking.data.remote

import com.sailinghawklabs.triviaking.data.remote.dto.CategoriesResponseDto
import com.sailinghawklabs.triviaking.data.remote.dto.CategoryStatsResponseDto
import com.sailinghawklabs.triviaking.data.remote.dto.QuestionsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenTriviaDatabaseApi {
    companion object {
        const val BASE_URL = "https://opentdb.com/"
    }

    @GET("api_category.php")
    suspend fun getCategories(): CategoriesResponseDto


    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") quantity: Int,
        @Query("category") categoryId: Int?,
        @Query("difficulty") difficulty: String?,
        ): QuestionsResponseDto

    @GET("api_count.php")
    suspend fun getCategoryStats(
        @Query("category") categoryId: Int,
    ): CategoryStatsResponseDto


}