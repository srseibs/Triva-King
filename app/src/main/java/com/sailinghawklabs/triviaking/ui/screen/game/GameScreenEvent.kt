package com.sailinghawklabs.triviaking.ui.screen.game

import com.sailinghawklabs.triviaking.domain.usecase.GetQuestionSet

sealed class GameScreenEvent {
    data class DifficultyChanged(val newDifficulty: GetQuestionSet.DIFFICULTY): GameScreenEvent()
    data class NumQuestionsChanged(val newNumQuestions: Int): GameScreenEvent()
}
