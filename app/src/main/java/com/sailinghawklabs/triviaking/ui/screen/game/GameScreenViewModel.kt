package com.sailinghawklabs.triviaking.ui.screen.game

import android.util.Log
import androidx.core.math.MathUtils
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY
import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import com.sailinghawklabs.triviaking.domain.model.defaultGamePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


const val MAX_NUM_QUESTIONS = 10
const val MIN_NUM_QUESTIONS = 2

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    val dataStore: DataStore<GamePreferences>
) : ViewModel() {

    private val _gameScreenState = MutableStateFlow(GameScreenState(
        gamePreferences = defaultGamePreferences
        )
    )
    val gameScreenState = _gameScreenState.asStateFlow()

    fun onGameEvent(event: GameScreenEvent) {
        when (event) {
            is GameScreenEvent.NumQuestionsChanged -> {
                processNumQuestionsChange(event.newNumQuestions)
            }

            is GameScreenEvent.DifficultyChanged -> {
                processDifficultyChanged(event.newDifficulty)
            }
        }
    }

    private fun processDifficultyChanged(newDifficulty: DIFFICULTY) {
        viewModelScope.launch {
            dataStore.updateData {
                it.copy(
                    difficulty = newDifficulty
                )
            }
        }
    }

    private fun processNumQuestionsChange(change:Int) {
        val newNumQuestions = gameScreenState.value.gamePreferences.numberOfQuestions + change
        viewModelScope.launch {
            dataStore.updateData {
                it.copy(
                    numberOfQuestions = MathUtils.clamp(
                        newNumQuestions,
                        MIN_NUM_QUESTIONS,
                        MAX_NUM_QUESTIONS)
                )
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

