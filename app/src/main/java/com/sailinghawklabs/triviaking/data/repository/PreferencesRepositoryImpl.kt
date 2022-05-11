package com.sailinghawklabs.triviaking.data.repository

import androidx.core.math.MathUtils
import androidx.datastore.core.DataStore
import com.sailinghawklabs.triviaking.domain.model.Category
import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY
import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import com.sailinghawklabs.triviaking.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<GamePreferences>
) : PreferencesRepository {

    val MAX_NUM_QUESTIONS = 10
    val MIN_NUM_QUESTIONS = 2

    override suspend fun updateNumQuestions(requestedNumber: Int) {
        dataStore.updateData {
            it.copy(
                numberOfQuestions = MathUtils.clamp(
                    requestedNumber,
                    MIN_NUM_QUESTIONS,
                    MAX_NUM_QUESTIONS
                )
            )
        }

    }

    override suspend fun updateDifficulty(difficulty: DIFFICULTY) {
        dataStore.updateData {
            it.copy(
                difficulty = difficulty,
            )
        }
    }

    override suspend fun updateCategory(category: Category) {
        dataStore.updateData {
            it.copy(
                category = category
            )
        }
    }

    override suspend fun setAllPreferences(preferences: GamePreferences) {
        dataStore.updateData {
            preferences
        }
    }

    override suspend fun getNumQuestions(): Int {
        return dataStore.data.first().numberOfQuestions
    }

    override suspend fun getDifficulty(): DIFFICULTY {
        return dataStore.data.first().difficulty
    }

    override suspend fun getCategory(): Category? {
        return dataStore.data.first().category
    }

    override suspend fun getAllPreferences(): GamePreferences {
        return dataStore.data.first()
    }


}