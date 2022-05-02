package com.sailinghawklabs.triviaking.ui.screen.category

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun CategorySelectScreen(
    viewModel: CategorySelectViewModel = hiltViewModel(),
) {

    val viewState = viewModel.categoryState.collectAsState().value

    CategorySelectContent(
        viewState = viewState,
    )
}


