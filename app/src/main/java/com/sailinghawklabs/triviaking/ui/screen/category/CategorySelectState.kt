package com.sailinghawklabs.triviaking.ui.screen.category

import com.sailinghawklabs.triviaking.domain.model.Category

sealed class CategorySelectState {
    object StartState : CategorySelectState()
    object LoadingState : CategorySelectState()

    data class ListAvailable(
        val categories: List<Category>
    ) : CategorySelectState()

    data class Error(val errorMessage: String) : CategorySelectState()

}