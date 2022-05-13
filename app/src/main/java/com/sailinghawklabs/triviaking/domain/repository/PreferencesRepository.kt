package com.sailinghawklabs.triviaking.domain.repository

import com.sailinghawklabs.triviaking.domain.model.Category
import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY
import com.sailinghawklabs.triviaking.domain.model.GamePreferences

interface PreferencesRepository  {

    suspend fun updateNumQuestions(requestedNumber: Int)
    suspend fun updateDifficulty(difficulty: DIFFICULTY)
    suspend fun updateCategory(category: Category)

    suspend fun getNumQuestions(): Int
    suspend fun getDifficulty(): DIFFICULTY
    suspend fun getCategory(): Category?

    suspend fun getAllPreferences(): GamePreferences
    suspend fun setAllPreferences(preferences: GamePreferences)

}