package com.sailinghawklabs.triviaking.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GamePreferences(
    val category: Category?,
    val categoryStats: CategoryStats?,
    val numberOfQuestions: Int,
    val difficulty: DIFFICULTY,
)

val gameCategoryALL:Category? = null

val defaultGamePreferences = GamePreferences(
    category = null,
    numberOfQuestions = 5,
    difficulty = DIFFICULTY.ANY,
    categoryStats = null,
)