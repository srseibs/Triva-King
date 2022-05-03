package com.sailinghawklabs.triviaking.ui.screen.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.sailinghawklabs.triviaking.domain.model.Question

@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel()
) {

    val viewState = viewModel.viewState.collectAsState()

    QuizScreenContent(viewState = viewState.value)

}