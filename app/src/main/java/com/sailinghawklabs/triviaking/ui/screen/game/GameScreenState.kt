package com.sailinghawklabs.triviaking.ui.screen.game

import com.sailinghawklabs.triviaking.domain.model.Question
import com.sailinghawklabs.triviaking.domain.usecase.GetQuestionSet

data class GameScreenState(
    val gameQuestions: List<Question> = emptyList(),
    val gameDifficulty: GetQuestionSet.DIFFICULTY = GetQuestionSet.DIFFICULTY.ANY,
    val numberOfQuestions: Int = 0,
    val gameCategory: String? = null,
    val gameCategoryId: Int? = null,
)
