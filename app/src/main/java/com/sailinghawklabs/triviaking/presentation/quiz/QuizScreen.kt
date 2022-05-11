package com.sailinghawklabs.triviaking.presentation.quiz

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    Log.d("QuizScreen", "QuizScreen: REDRAWING")

    QuizScreenContent(
        viewState = viewModel.screenState,
        onViewEvent = viewModel::onQuizEvent,
    )

}