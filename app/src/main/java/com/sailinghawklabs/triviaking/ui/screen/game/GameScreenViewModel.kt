package com.sailinghawklabs.triviaking.ui.screen.game

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(

): ViewModel() {

    private val _gameScreenState = MutableStateFlow(GameScreenState())
    val gameScreenState = _gameScreenState.asStateFlow()
}