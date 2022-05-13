package com.sailinghawklabs.triviaking.presentation.game

import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY


sealed class GameScreenEvent {
    data class DifficultyChanged(val newDifficulty: DIFFICULTY): GameScreenEvent()
    data class NumQuestionsChanged(val newNumQuestions: Int): GameScreenEvent()
}
