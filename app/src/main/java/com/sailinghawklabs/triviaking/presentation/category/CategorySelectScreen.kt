package com.sailinghawklabs.triviaking.presentation.category

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CategorySelectScreen(
    viewModel: CategorySelectViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val viewState = viewModel.categoryState.collectAsState().value

    if(viewState is CategorySelectState.Dismissed) {
        navigator.navigateUp()
    }

    CategorySelectContent(
        viewState = viewState,
        onBackClicked = {navigator.popBackStack() },
        onCategoryClicked = { viewModel.updateCategory(it)}
    )
}


