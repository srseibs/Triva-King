package com.sailinghawklabs.triviaking.ui.screen.game

import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY


sealed class GameScreenEvent {
    data class DifficultyChanged(val newDifficulty: DIFFICULTY): GameScreenEvent()
    data class NumQuestionsChanged(val newNumQuestions: Int): GameScreenEvent()
}
