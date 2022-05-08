package com.sailinghawklabs.triviaking.ui.screen.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sailinghawklabs.triviaking.domain.model.Question

@Destination
@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {

    val viewState = viewModel.viewState.collectAsState()

    QuizScreenContent(viewState = viewState.value)

}