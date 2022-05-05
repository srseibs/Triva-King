package com.sailinghawklabs.triviaking.ui.screen.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import com.sailinghawklabs.triviaking.ui.screen.destinations.CategorySelectScreenDestination

@Destination(start = true)
@Composable
fun GameScreen(
    viewModel: GameScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    categoryResultRecipient: ResultRecipient<CategorySelectScreenDestination, String>,
) {

    val viewState = viewModel.gameScreenState.collectAsState().value

    categoryResultRecipient.onNavResult{ result ->
        when (result) {
            is NavResult.Canceled -> {

            }
            is NavResult.Value -> {
                viewModel.onCategoryChanged(newCategoryName = result.value)
            }
        }

    }

    GameScreenContent(viewState, navigator)
}


