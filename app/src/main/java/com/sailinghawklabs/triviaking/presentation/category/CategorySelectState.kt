package com.sailinghawklabs.triviaking.presentation.category

import com.sailinghawklabs.triviaking.domain.model.Category

sealed class CategorySelectState {
    object StartState : CategorySelectState()
    object LoadingState : CategorySelectState()

    data class ListAvailable(
        val categories: List<Category>
    ) : CategorySelectState()

    object Dismissed : CategorySelectState()

    data class Error(val errorMessage: String) : CategorySelectState()

}