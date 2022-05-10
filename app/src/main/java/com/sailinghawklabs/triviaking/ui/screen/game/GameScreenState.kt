package com.sailinghawklabs.triviaking.ui.screen.game

import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import com.sailinghawklabs.triviaking.domain.model.defaultGamePreferences

data class GameScreenState(
    val gamePreferences: GamePreferences = defaultGamePreferences,
)
