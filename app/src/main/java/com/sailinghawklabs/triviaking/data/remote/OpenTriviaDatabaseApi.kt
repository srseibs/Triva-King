package com.sailinghawklabs.triviaking.data.remote

import com.sailinghawklabs.triviaking.data.remote.dto.CategoriesResponseDto
import retrofit2.http.GET

interface OpenTriviaDatabaseApi {
    companion object {
        const val BASE_URL = "https://opentdb.com/"
    }

    @GET("api_category.php")
    suspend fun getCategories(): CategoriesResponseDto

}