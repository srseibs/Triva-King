package com.sailinghawklabs.triviaking.domain.model

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
    val difficulty: DIFFICULTY,
)

val gameCategoryALL:Category? = null

val defaultGamePreferences = GamePreferences(
    category = null,
    numberOfQuestions = 5,
    difficulty = DIFFICULTY.ANY
)