package com.sailinghawklabs.triviaking.ui.screen.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun GameScreen(
    viewModel: GameScreenViewModel = hiltViewModel(),
) {

    val viewState = viewModel.gameScreenState.collectAsState().value

    GameScreenContent(viewState)
}


