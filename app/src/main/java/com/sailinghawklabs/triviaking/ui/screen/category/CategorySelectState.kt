package com.sailinghawklabs.triviaking.ui.screen.category

sealed class CategorySelectState {
    object StartState : CategorySelectState()
    object LoadingState : CategorySelectState()

    data class ListAvailable(
        val categories: List<String>
    ) : CategorySelectState()

    data class Error(val errorMessage: String) : CategorySelectState()

}