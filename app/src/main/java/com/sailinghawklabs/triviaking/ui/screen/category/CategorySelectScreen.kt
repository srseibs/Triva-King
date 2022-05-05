package com.sailinghawklabs.triviaking.ui.screen.category

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator

@Destination
@Composable
fun CategorySelectScreen(
    viewModel: CategorySelectViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    categoryResultNavigator: ResultBackNavigator<String>,
) {

    val viewState = viewModel.categoryState.collectAsState().value

    CategorySelectContent(
        viewState = viewState,
        navigator = navigator,
        categoryResultNavigator = categoryResultNavigator,
    )
}


