package com.sailinghawklabs.triviaking.ui.screen.game

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sailinghawklabs.triviaking.domain.model.Category
import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import com.sailinghawklabs.triviaking.domain.usecase.GetQuestionSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    val dataStore: DataStore<GamePreferences>
) : ViewModel() {

    private val _gameScreenState = MutableStateFlow(GameScreenState(
        gamePreferences = GamePreferences(
            category = Category("Famous Dogs", 12),
            numberOfQuestions = 4,
            difficulty = GetQuestionSet.DIFFICULTY.HARD,
        )
    ))
    val gameScreenState = _gameScreenState.asStateFlow()

    fun onGameEvent(event: GameScreenEvent) {
        when (event) {
            is GameScreenEvent.DifficultyChanged -> {
                viewModelScope.launch {
                    dataStore.updateData {
                        it.copy(
                            difficulty = event.newDifficulty
                        )
                    }
                }
            }
        }
    }

    init {
        Log.d("GameScreenViewModel", "init called")
        viewModelScope.launch{
            observePreferences()
        }
    }

    private suspend fun observePreferences() {
        dataStore.data.collect{
            Log.d("GameScreenViewModel", "observePreferences: $it")
            _gameScreenState.value = _gameScreenState.value.copy(
                gamePreferences = it,
            )
        }
    }
}

