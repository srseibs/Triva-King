package com.sailinghawklabs.triviaking.ui.screen.category

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun CategorySelectScreen(
    viewModel: CategorySelectViewModel = hiltViewModel(),
) {

    val viewState = viewModel.categoryState.collectAsState().value

    CategorySelectContent(
        viewState = viewState,
    )
}


