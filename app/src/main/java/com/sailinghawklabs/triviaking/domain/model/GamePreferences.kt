package com.sailinghawklabs.triviaking.domain.model

import com.sailinghawklabs.triviaking.domain.usecase.GetQuestionSet
import kotlinx.serialization.Serializable

/**
 * @param categoryId
 * @param numberOfQuestions
 * @param difficulty
 */

@Serializable
data class GamePreferences(
    val category: Category?,
    val numberOfQuestions: Int,
    val difficulty: GetQuestionSet.DIFFICULTY,
)

val defaultGamePreferences = GamePreferences(
    category = null,
    numberOfQuestions = 5,
    difficulty = GetQuestionSet.DIFFICULTY.ANY
)