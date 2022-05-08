package com.sailinghawklabs.triviaking.ui.screen.game

import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import com.sailinghawklabs.triviaking.domain.model.Question
import com.sailinghawklabs.triviaking.domain.model.defaultGamePreferences
import com.sailinghawklabs.triviaking.domain.usecase.GetQuestionSet

data class GameScreenState(
    val gameQuestions: List<Question> = emptyList(),  //? is this right? Screen doesn't use it
    val gamePreferences: GamePreferences = defaultGamePreferences,
)
