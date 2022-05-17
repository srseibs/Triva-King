package com.sailinghawklabs.triviaking.presentation.category

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.sailinghawklabs.triviaking.domain.model.Category
import com.sailinghawklabs.triviaking.domain.model.CategoryStats
import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import com.sailinghawklabs.triviaking.domain.repository.QuizRepository
import com.sailinghawklabs.triviaking.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategorySelectViewModel @Inject constructor(

    private val triviaRepository: QuizRepository,

    // need to use repository
    private val dataStore: DataStore<GamePreferences>


) : ViewModel() {

    private val _categoryState =
        MutableStateFlow<CategorySelectState>(CategorySelectState.StartState)
    val categoryState = _categoryState.asStateFlow()

    init {
        fetchCategories()
    }

    fun updateCategory(newCategory: Category?) {
        viewModelScope.launch {

            var categoryStats : CategoryStats? = null

            if (newCategory != null) {
                try {

                    triviaRepository.fetchCategoryStats(newCategory.id).collect { result ->
                        when (result) {
                            is Result.Loading -> {

                            }
                            is Result.Success -> {
                                categoryStats = result.data
                            }
                            is Result.Error -> {

                            }
                        }
                    }
                } catch (e: Exception) {
                    println(e.localizedMessage ?: "Unknown Error retrieving category stats.")
                }
            }

            storeCategory(newCategory, categoryStats)
        }
    }

    private suspend fun storeCategory(
        newCategory: Category?,
        newStats: CategoryStats?,
    ) {
        dataStore.updateData {
            it.copy(
                category = newCategory,
                categoryStats = newStats,
            )
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                _categoryState.tryEmit(CategorySelectState.LoadingState)

                triviaRepository.fetchCategories().collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            _categoryState.emit(CategorySelectState.LoadingState)
                        }
                        is Result.Success -> {
                            result.data?.let { categories ->
                                val sortedCategories = categories.map { it }.sortedBy { it.name }
                                _categoryState.emit(
                                    CategorySelectState.ListAvailable(
                                        categories = sortedCategories
                                    )
                                )
                            }
                        }
                        is Result.Error -> {
                            _categoryState.emit(
                                CategorySelectState.Error(
                                    errorMessage = result.message ?: "Missing error message."
                                )
                            )
                        }
                    }
                }

            } catch (e: Exception) {
                _categoryState.emit(
                    CategorySelectState.Error(e.localizedMessage ?: "Unknown error [try]")
                )
            }
        }
    }

}