package com.sailinghawklabs.triviaking.presentation.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun GameScreen(
    viewModel: GameScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {

    val viewState = viewModel.gameScreenState.collectAsState().value

    GameScreenContent(
        viewState = viewState,
        navigator = navigator,
        onScreenEvent = viewModel::onGameEvent
    )
}


