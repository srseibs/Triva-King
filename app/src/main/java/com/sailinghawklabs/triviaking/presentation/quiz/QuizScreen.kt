package com.sailinghawklabs.triviaking.presentation.quiz

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sailinghawklabs.triviaking.presentation.destinations.CategorySelectScreenDestination
import com.sailinghawklabs.triviaking.presentation.destinations.GameScreenDestination

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
        onSettingsPressed = {
            navigator.popBackStack(
                route = GameScreenDestination,
                inclusive = false,
            )
        }
    )

}